package com.pk.ai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.pk.ai.cloud.dao")
public class CustomerServiceRunner {

  // https://github.com/spring-cloud/spring-cloud-commons/issues/355
  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceRunner.class, args);
  }

}
