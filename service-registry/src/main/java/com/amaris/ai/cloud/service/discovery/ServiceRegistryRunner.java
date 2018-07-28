package com.amaris.ai.cloud.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableEurekaServer
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRegistryRunner {

  public static void main(String[] args) {
    SpringApplication.run(ServiceRegistryRunner.class, args);
  }

}
