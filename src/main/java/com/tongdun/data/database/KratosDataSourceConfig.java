package com.tongdun.data.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置kratos_mysql数据源
 * @author yxw
 *
 */
@Configuration
@MapperScan(basePackages = KratosDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "kratosSqlSessionFactory")
public class KratosDataSourceConfig {

	private final static Logger logger = LoggerFactory.getLogger(KratosDataSourceConfig.class);

	// 精确到 master 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.tongdun.data.mapper.kratos";
	static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";

	@Value("${kratos.database.url}")
	private String url;

	@Value("${kratos.database.username}")
	private String user;

	@Value("${kratos.database.password}")
	private String password;

	@Value("${kratos.database.driver}")
	private String driverClass;

	@Bean(name = "kratosDataSource")
	@Primary
	public DataSource kratosDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean(name = "kratosTransactionManager")
	@Primary
	public DataSourceTransactionManager kratosTransactionManager() {
		return new DataSourceTransactionManager(kratosDataSource());
	}

	@Bean(name = "kratosSqlSessionFactory")
	@Primary /* 此处必须在主数据库的数据源配置上加上@Primary */
	public SqlSessionFactory kratosSqlSessionFactory(@Qualifier("kratosDataSource") DataSource kratosDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(kratosDataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(KratosDataSourceConfig.MAPPER_LOCATION));
		logger.info("加载kratos_mysql数据库连接~~~~~~~~~~~~~~~");
		return sessionFactory.getObject();
	}
}