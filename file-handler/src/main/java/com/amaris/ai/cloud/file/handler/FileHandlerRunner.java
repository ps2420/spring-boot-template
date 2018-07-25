package com.amaris.ai.cloud.file.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class FileHandlerRunner {

  public static void main(String[] args) {
    SpringApplication.run(FileHandlerRunner.class, args);
  }
}
