package com.example.hoponuserservice.dtos;

import com.example.hoponuserservice.model.Driver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverCreationResponseDto {
    private Driver driver;
    private ResponseStatus responseStatus;

}
