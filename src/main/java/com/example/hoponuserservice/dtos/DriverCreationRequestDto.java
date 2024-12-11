package com.example.hoponuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverCreationRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
}
