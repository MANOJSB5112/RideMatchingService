package com.example.hoponuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiderCreationRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private double latitude;
    private double longitude;
}
