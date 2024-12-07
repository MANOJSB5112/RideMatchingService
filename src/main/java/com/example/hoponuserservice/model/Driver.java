package com.example.hoponuserservice.model;

import com.example.hoponuserservice.DriverService.Service.DiverService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    private DriverStatus driverStatus;
}
