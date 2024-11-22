package com.example.hoponuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequest {
    private String riderId;
    private Location source;
    private Location destination;


    @Getter
    @Setter
    public static class Location {
        private double latitude;
        private double longitude;

    }
}
