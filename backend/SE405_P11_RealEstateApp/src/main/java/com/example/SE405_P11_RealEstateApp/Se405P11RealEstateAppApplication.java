package com.example.SE405_P11_RealEstateApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//disable security
@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
    org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
public class Se405P11RealEstateAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Se405P11RealEstateAppApplication.class, args);
    }

}
