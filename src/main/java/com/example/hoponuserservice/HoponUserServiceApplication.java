package com.example.hoponuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HoponUserServiceApplication{
    public static void main(String[] args) {
        System.out.println("I have reached main");
        SpringApplication.run(HoponUserServiceApplication.class, args);
    }

}
