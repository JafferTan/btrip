package com.jaffer.btrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class BtripApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtripApplication.class, args);
    }

}
