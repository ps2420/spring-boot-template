package com.amaris.ai.cloud.db;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import com.amaris.ai.cloud.db.DBServiceRunner;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.amaris.ai.cloud.db"}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = {DBServiceRunner.class})})
public class ITTestSetup {

}
