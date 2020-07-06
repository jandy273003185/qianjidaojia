package com.qifenqian.bms.configure.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 数据源配置， stcdb 主库
 *
 * @author xun.sun
 */
@Configuration
@MapperScan(basePackages = "com.qifenqian.bms", sqlSessionFactoryRef = "stcsSqlSessionFactory", annotationClass = MapperScanCore.class)
public class StcsDataSourceConfigure {

	@ConfigurationProperties("spring.datasource.stcs")
	@Bean(name = "stcsDataSource")
	public DruidDataSource stcdbDataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "stcsTransactionManager")
	public DataSourceTransactionManager combinedmasterTransactionManager(@Qualifier("stcsDataSource") DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean(name = "stcsSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("stcsDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setConfigLocation(
				new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/MyBatisMapConfig.xml"));
		return sessionFactoryBean.getObject();
	}

}
