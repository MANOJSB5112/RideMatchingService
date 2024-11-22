package com.example.hoponuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationUpdateRequestDto {
    private String riderId;
    private double latitude;
    private double longitude;

}