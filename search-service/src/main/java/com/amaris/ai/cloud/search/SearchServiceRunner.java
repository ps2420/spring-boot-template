package com.amaris.ai.cloud.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
public class SearchServiceRunner {

  public static void main(String[] args) {
    SpringApplication.run(SearchServiceRunner.class, args);
  }

}
