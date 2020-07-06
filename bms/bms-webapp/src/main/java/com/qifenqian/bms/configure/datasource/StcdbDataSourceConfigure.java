package com.qifenqian.bms.configure.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 数据源配置， 暂时不使用
 *
 * @author xun.sun
 */
@Configuration
@MapperScan(basePackages = "com.qifenqian.bms", sqlSessionFactoryRef = "stcdbSqlSessionFactory", annotationClass = MapperScan.class)
public class StcdbDataSourceConfigure {

	@Bean(name = "stcdbDataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.stcdb")
	public DataSource stcdbDataSource() {
		DruidDataSource ds = DruidDataSourceBuilder.create().build();
		return ds;
	}

	@Bean(name = "stcdbTransactionManager")
	@Primary
	public DataSourceTransactionManager stcdbTransactionManager(@Qualifier("stcdbDataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean(name = "stcdbSqlSessionFactory")
	@Primary
	public SqlSessionFactory stcdbSqlSessionFactory(@Qualifier("stcdbDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setConfigLocation(
				new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/MyBatisMapConfig.xml"));
		return sessionFactoryBean.getObject();
	}

}
