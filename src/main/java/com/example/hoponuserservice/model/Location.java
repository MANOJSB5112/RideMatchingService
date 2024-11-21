package com.example.hoponuserservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamoDBDocument
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @DynamoDBAttribute(attributeName = "latitude")
    private double latitude;

    @DynamoDBAttribute(attributeName = "longitude")
    private double longitude;
}

