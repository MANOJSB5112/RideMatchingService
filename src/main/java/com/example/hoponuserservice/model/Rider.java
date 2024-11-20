package com.example.hoponuserservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@DynamoDBTable(tableName = "rider")
public class Rider {
    @Id
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "phoneNumber")
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = "location")
    private Location location;
}
