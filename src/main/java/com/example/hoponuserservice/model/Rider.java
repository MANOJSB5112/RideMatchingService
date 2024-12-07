package com.example.hoponuserservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rider {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

}
