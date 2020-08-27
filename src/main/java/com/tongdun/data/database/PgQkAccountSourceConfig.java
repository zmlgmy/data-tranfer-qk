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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置qk_postgresql数据源
 * 
 * @author yxw
 *
 */
@Configuration
@MapperScan(basePackages = PgQkAccountSourceConfig.PACKAGE, sqlSessionFactoryRef = "pgQkAccSqlSessionFactory")
public class PgQkAccountSourceConfig {
	private final static Logger logger = LoggerFactory.getLogger(PgQkAccountSourceConfig.class);

	// 精确到 master 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.tongdun.data.mapper.pgqk.account";
	static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";

	@Value("${qk.account.datasource.url}")
	private String url;

	@Value("${qk.account.datasource.username}")
	private String user;

	@Value("${qk.account.datasource.password}")
	private String password;

	@Value("${qk.account.datasource.driver}")
	private String driverClass;

	@Bean(name = "pgQkAccSource")
	public DataSource pgQkDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean(name = "pgQkAccTransactionManager")
	public DataSourceTransactionManager pgQkTransactionManager(@Qualifier("pgQkAccSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "pgQkAccSqlSessionFactory")
//    @Primary /*此处必须在主数据库的数据源配置上加上@Primary*/
	public SqlSessionFactory pgQkSqlSessionFactory(@Qualifier("pgQkAccSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		/* 加载mybatis全局配置文件 */
		// bean.setConfigLocation(new
		// PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
		/* 加载所有的mapper.xml映射文件 */
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(PgQkAccountSourceConfig.MAPPER_LOCATION));
		// 添加插件 （改为使用配置文件加载了）
//        bean.setPlugins(new Interceptor[]{pageHelper()});
		logger.info("加载qk_postgresql_acc数据库连接~~~~~~~~~~~~~~~");
		return bean.getObject();
	}

//    @Bean(name = "paSqlSessionTemplate")
////    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("paSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

//    @Bean
//    public PageHelper pageHelper(){
//       // logger.info("MyBatis分页插件PageHelper");
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }

}
