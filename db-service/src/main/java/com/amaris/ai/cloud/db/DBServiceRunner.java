package com.amaris.ai.cloud.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class DBServiceRunner {

  // https://github.com/spring-cloud/spring-cloud-commons/issues/355
  public static void main(String[] args) {
    SpringApplication.run(DBServiceRunner.class, args);
  }

}
