package com.zhaotm.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhaotianming1
 * @date 2018/3/29
 */
@EnableAutoConfiguration
@ComponentScan()
@SpringBootApplication
public class SpringBoot  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }
}
