package com.example.hoponuserservice.AWSLocationService.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.location.LocationClient;

@Configuration
public class AmazonLocationServiceConfig {
    @Value("${aws.location.accessKey}")
    private String accessKey;

    @Value("${aws.location.secretKey}")
    private String secretKey;

    @Bean
    public LocationClient locationClient() {
        return LocationClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.EU_NORTH_1)
                .build();
    }
}