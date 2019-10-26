package com.pk.ai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@SpringBootApplication
@EnableTransactionManagement
public class CustomerServiceRunner {
 
  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceRunner.class, args);
  }

}
