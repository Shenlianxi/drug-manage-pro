package com.ytdsuda.management;

import com.ytdsuda.management.config.WebManageConfig;
import com.ytdsuda.management.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.ytdsuda.management.mappers")
public class ManagementApplication  extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }
}
