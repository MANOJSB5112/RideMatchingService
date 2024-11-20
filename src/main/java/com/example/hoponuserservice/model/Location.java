package com.example.hoponuserservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@DynamoDBDocument
@Getter
@Setter
public class Location {
    @DynamoDBAttribute(attributeName = "latitude")
    private double latitude;
    @DynamoDBAttribute(attributeName = "longitude")
    private double longitude;
}
