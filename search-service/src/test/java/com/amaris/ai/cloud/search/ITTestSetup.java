package com.amaris.ai.cloud.search;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.amaris.ai.cloud.search"}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SearchServiceRunner.class})})
public class ITTestSetup {

}
