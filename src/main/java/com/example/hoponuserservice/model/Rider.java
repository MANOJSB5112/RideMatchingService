package com.example.hoponuserservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rider extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;
}
