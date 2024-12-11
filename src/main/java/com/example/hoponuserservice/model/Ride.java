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
public class Ride extends BaseModel{
    @OneToOne
    private Rider rider;
    @OneToOne
    private Driver driver;
    @OneToOne
    private  Location source;
    @OneToOne
    private  Location destination;
    @Enumerated(EnumType.STRING)
    private RideStatus status;
    private double fare;
    private double distance;
    private String duration;
    private String eta;
}