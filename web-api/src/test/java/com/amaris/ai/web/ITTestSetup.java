package com.amaris.ai.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {WebApiRunner.class})})
public class ITTestSetup {

}
