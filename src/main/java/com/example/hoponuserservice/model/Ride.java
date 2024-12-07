package com.example.hoponuserservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ride {
    private long id;
    private Rider rider;
    private Driver driver;
    private  Location source;
    private  Location destination;
    private RideStatus status;
    private double fare;
}