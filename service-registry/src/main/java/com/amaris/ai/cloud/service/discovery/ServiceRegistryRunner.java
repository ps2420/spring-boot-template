package com.amaris.ai.cloud.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableEurekaServer
@SpringBootApplication
@EnableAdminServer
public class ServiceRegistryRunner {

  public static void main(String[] args) {
    SpringApplication.run(ServiceRegistryRunner.class, args);
  }

}
