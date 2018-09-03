package com.amaris.ai.cloud.db.config;

import static java.lang.Integer.parseInt;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

  // https://github.com/spring-cloud/spring-cloud-commons/issues/355
  @Autowired
  private Environment environment;

  private final String SPRING_DS = "spring.datasource.";

  private String dataSourceValue(final String dataSourceProp) {
    return environment.getRequiredProperty(dataSourceProp);
  }

  @Bean
  public HikariConfig hikarConfig() {
    final HikariConfig config = new HikariConfig();
    config.setDriverClassName(dataSourceValue(SPRING_DS + "driver-class-name"));
    config.setJdbcUrl(dataSourceValue(SPRING_DS + "url"));
    config.setUsername(dataSourceValue(SPRING_DS + "username"));
    config.setPassword(dataSourceValue(SPRING_DS + "password"));
    config.setMaximumPoolSize(parseInt(dataSourceValue(SPRING_DS + "maximumPoolSize")));
    config.setMinimumIdle(parseInt(dataSourceValue(SPRING_DS + "minimumIdle")));
    config.setConnectionTestQuery("SELECT 1");
    config.setAutoCommit(false);
    config.addDataSourceProperty("poolName", dataSourceValue("spring.application.name"));
    return config;
  }

  @Bean(destroyMethod = "close")
  public HikariDataSource dataSource(final @Autowired HikariConfig hikarConfig) {
    return new HikariDataSource(hikarConfig);
  }

  @Bean
  public JdbcTemplate jdbcTemplate(final @Autowired DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
  
  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(final @Autowired DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }


}
