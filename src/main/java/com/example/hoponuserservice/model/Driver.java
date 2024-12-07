package com.example.hoponuserservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Driver extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
}
