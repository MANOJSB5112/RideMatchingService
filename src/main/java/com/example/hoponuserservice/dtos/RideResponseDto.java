package com.example.hoponuserservice.dtos;

import com.example.hoponuserservice.model.Ride;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideResponseDto {
    private Ride ride;
    private ResponseStatus responseStatus;
}
