package com.qifenqian.bms.platform.web.page.plugin;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.qifenqian.bms.platform.common.utils.ReflectUtils;
import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.page.bean.PageInfo;

/**
 * MyBatis针对Oracle定制的分页插件
 * 
 * @project gyzb-platform-web
 * @fileName MybatisPagePluginOracle.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class MybatisPagePluginOracle implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		 
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtils.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getValueByFieldName(delegate, "mappedStatement");

			// 如果线程变量中标志查询要分页,则需要进行处理
			if (ThreadUtils.get(Constants.Platform.$_BY_PAGE) == Boolean.TRUE) {
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空  
				Connection connection = (Connection) invocation.getArgs()[0];
				String sql = boundSql.getSql();
				String countSql = "select count(1) from (" + sql + ")"; //记录统计  
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
				// 将元参数域设置到countBs中,否则在<foreach>子句中会报错
				Field metaParamField = ReflectUtils.getFieldByFieldName(boundSql, "metaParameters");
				if(metaParamField != null){
					MetaObject mo = (MetaObject) ReflectUtils.getValueByFieldName(boundSql, "metaParameters");
					ReflectUtils.setValueByFieldName(countBS, "metaParameters", mo);
				}
				// 如果没有参数
				if (parameterObject != null) {
					setParameters(countStmt, mappedStatement, countBS, parameterObject);
				}
				ResultSet rs = countStmt.executeQuery();
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				countStmt.close();
				
				PageInfo page = (PageInfo) ThreadUtils.get(Constants.Platform.$_PAGEINFO);
				page.setRowCnt(count);
				
				// 包装分页SQL
				String pageSql = generatePageSql(sql, page);
				ReflectUtils.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.  
			}
		}

		return invocation.proceed();
	}

	/** 
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler 
	 * @param ps 
	 * @param mappedStatement 
	 * @param boundSql 
	 * @param parameterObject org.sevenpay.platform.annotation.Page
	 * @throws SQLException 
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement "
								+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	/** 
	 * 根据Oracle的语法，生成特定的分页sql 
	 * @param sql 
	 * @param page 
	 * @return 
	 */
	private String generatePageSql(String sql, PageInfo page) {
		
		if (page != null) {
			StringBuilder pageSql = new StringBuilder();
			pageSql.append("SELECT * FROM (  SELECT SORTTABLE.*, ROWNUM AS TABLEROWNUM FROM(");
			pageSql.append(sql);
			pageSql.append(") SORTTABLE WHERE ROWNUM <= ");
			pageSql.append(page.getLastIdx());
			pageSql.append(") WHERE TABLEROWNUM >= ");
			pageSql.append(page.getFirstIdx());
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		// NOP
	}

}