package com.pk.ai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration; 

@Configuration 
@SpringBootApplication
public class CustomerUIRunner {
 
  public static void main(String[] args) {
    SpringApplication.run(CustomerUIRunner.class, args);
  }

}
