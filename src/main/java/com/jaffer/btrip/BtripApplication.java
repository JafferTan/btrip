package com.jaffer.btrip;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BtripApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtripApplication.class, args);
    }

}
