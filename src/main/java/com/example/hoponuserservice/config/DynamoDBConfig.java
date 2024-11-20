package com.example.hoponuserservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.mapping.DynamoDBMappingContext;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.repository") // Replace with your package
public class DynamoDBConfig {

    @Value("${aws.access.key})")
    private String aws_access_key;

    @Value("${aws.access.secret-key}")
    private String aws_secret_access_key;
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(aws_access_key, aws_secret_access_key);
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    @Bean
    public DynamoDBMappingContext dynamoDBMappingContext() {
        return new DynamoDBMappingContext();
    }
}