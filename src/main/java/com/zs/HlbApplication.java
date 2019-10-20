package com.zs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class HlbApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlbApplication.class, args);
    }

}
