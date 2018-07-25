package com.amaris.ai.cloud.file.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class FileUploadRunner {

  public static void main(String[] args) {
    SpringApplication.run(FileUploadRunner.class, args);
  }
}
