package com.pk.ai.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.pk.ai.cloud"}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {DBServiceRunner.class})})
public class ITTestSetup {

}
