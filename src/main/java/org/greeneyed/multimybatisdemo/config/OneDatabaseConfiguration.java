/**
 * <br>
 * Created Date : Nov 14, 2018 9:49:29 PM Copyright©2002 Nbware All rights reserved.
 * 
 * @author hoyacom
 */
package org.greeneyed.multimybatisdemo.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * <br>
 * Created Date : Nov 14, 2018
 * 
 * @author hoyacom
 * @since
 *
 */
@Configuration
@MapperScan(value = "org.greeneyed.multimybatisdemo.mappers.one",
    sqlSessionTemplateRef = "oneSessionTemplatae")
@EnableTransactionManagement
public class OneDatabaseConfiguration
{
  // @Bean(name = "oneDS", destroyMethod = "")
  // @ConfigurationProperties(prefix = "spring.one.datasources.hikari")
  // @Primary
  // public DataSource dataSourceOne()
  // {
  // // Filled up with the properties specified about thanks to Spring Boot black
  // // magic
  // return new HikariDataSource();
  // }


  @Bean
  @ConfigurationProperties(prefix = "spring.one.datasource.hikari")
  public HikariConfig hikariConfigOne()
  {
    return new HikariConfig();
  }

  @Bean(name = "oneDS", destroyMethod = "")
  @Primary
  public DataSource dataSourceOne(@Qualifier("hikariConfigOne") HikariConfig hikariConfigOne)
  {
    return new HikariDataSource(hikariConfigOne);
  }

  @Bean(name = "oneTxm")
  @Primary
  public DataSourceTransactionManager oneTxm(@Qualifier("oneDS") DataSource oneDatasource)
  {
    return new DataSourceTransactionManager(oneDatasource);
  }

  @Bean(name = "oneSessionFactory", destroyMethod = "")
  @Primary
  public SqlSessionFactory sqlSessionFactory(@Named("oneDS") final DataSource oneDataSource)
      throws Exception
  {
    final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(oneDataSource);
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
    // Various other SqlSessionFactory settings
    return sqlSessionFactory;
  }

  @Bean(name = "oneSessionTemplatae")
  @Primary
  public SqlSessionTemplate amsdbSqlSessionTemplate(
      @Qualifier("oneSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception
  {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}
