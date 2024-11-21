package com.example.hoponuserservice;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableDynamoDBRepositories(basePackages = "com.example.hoponuserservice.repository")
public class HoponUserServiceApplication{
    public static void main(String[] args) {
        System.out.println("I have reached main");
        SpringApplication.run(HoponUserServiceApplication.class, args);
    }
}
