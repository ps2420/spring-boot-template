package com.amaris.ai.cloud.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ApiGatewayRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayRunner.class, args);
    }

}