package com.example.hoponuserservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequestDto {
    private long riderId;
    private double sourceLat;
    private double sourceLong;
    private double destLat;
    private double destLong;
}
