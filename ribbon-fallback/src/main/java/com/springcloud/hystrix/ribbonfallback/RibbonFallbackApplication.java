package com.springcloud.hystrix.ribbonfallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RibbonFallbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonFallbackApplication.class, args);
    }

}
