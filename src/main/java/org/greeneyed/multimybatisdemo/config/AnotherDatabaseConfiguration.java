/**
 * <br>
 * Created Date : Nov 14, 2018 9:50:28 PM Copyright©2002 Nbware All rights reserved.
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
@MapperScan(value = "org.greeneyed.multimybatisdemo.mappers.another",
    sqlSessionTemplateRef = "anotherSessionTemplatae")
@EnableTransactionManagement
public class AnotherDatabaseConfiguration
{
  // @Bean(name = "anotherDS", destroyMethod = "")
  // @ConfigurationProperties(prefix = "spring.another.datasources.hikari")
  // public DataSource dataSourceAnother()
  // {
  // // Filled up with the properties specified about thanks to Spring Boot black
  // // magic
  // return new HikariDataSource();
  // }


  @Bean
  @ConfigurationProperties(prefix = "spring.another.datasource.hikari")
  public HikariConfig hikariConfigAnother()
  {
    return new HikariConfig();
  }

  @Bean(name = "anotherDS", destroyMethod = "")
  public DataSource dataSourceAnother(
      @Qualifier("hikariConfigAnother") HikariConfig hikariConfigAnother)
  {
    return new HikariDataSource(hikariConfigAnother);
  }

  @Bean(name = "anotherTxm")
  public DataSourceTransactionManager anotherTxm(
      @Qualifier("anotherDS") DataSource anotherDatasource)
  {
    return new DataSourceTransactionManager(anotherDatasource);
  }

  @Bean(name = "anotherSessionFactory", destroyMethod = "")
  public SqlSessionFactory censoSqlSessionFactory(
      @Named("anotherDS") final DataSource anotherDataSource) throws Exception
  {
    final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(anotherDataSource);
    final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
    // Various other SqlSessionFactory settings
    return sqlSessionFactory;
  }

  @Bean(name = "anotherSessionTemplatae")
  public SqlSessionTemplate amsdbSqlSessionTemplate(
      @Qualifier("anotherSessionFactory") SqlSessionFactory anotherSessionFactory) throws Exception
  {
    return new SqlSessionTemplate(anotherSessionFactory);
  }
}
