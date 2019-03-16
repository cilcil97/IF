package com.licitacija.mk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ServletComponentScan
public class MkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MkApplication.class, args);
    }

}
