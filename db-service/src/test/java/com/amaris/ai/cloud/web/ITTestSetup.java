package com.amaris.ai.cloud.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import com.amaris.ai.cloud.db.DBServiceRunner;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {DBServiceRunner.class})})
public class ITTestSetup {

}
