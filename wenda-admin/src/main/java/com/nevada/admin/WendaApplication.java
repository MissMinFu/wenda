package com.nevada.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.nevada.dao")
public class WendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WendaApplication.class, args);
    }
}
