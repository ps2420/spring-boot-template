package com.amaris.ai.cloud.search;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SearchServiceRunner.class})})
public class ITTestSetup {

}
