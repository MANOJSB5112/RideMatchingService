package com.example.hoponuserservice;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class HoponUserServiceApplication{
    public static void main(String[] args) {
        System.out.println("I have reached main");
        SpringApplication.run(HoponUserServiceApplication.class, args);
    }
}
