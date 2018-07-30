package com.amaris.ai.cloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
public class WebApiRunner {

  public static void main(String[] args) {
    SpringApplication.run(WebApiRunner.class, args);
  }

}
