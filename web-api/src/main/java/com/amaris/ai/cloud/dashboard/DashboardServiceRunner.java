package com.amaris.ai.cloud.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
public class DashboardServiceRunner {

  public static void main(String[] args) {
    SpringApplication.run(DashboardServiceRunner.class, args);
  }

}
