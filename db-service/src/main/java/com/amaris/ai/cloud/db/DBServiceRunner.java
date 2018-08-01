package com.amaris.ai.cloud.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
//@EnableAutoConfiguration
@SpringBootApplication
public class DBServiceRunner {

  public static void main(String[] args) {
    SpringApplication.run(DBServiceRunner.class, args);
  }

}
