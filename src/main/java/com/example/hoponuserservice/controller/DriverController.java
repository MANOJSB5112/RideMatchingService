package com.example.hoponuserservice.controller;

import com.example.hoponuserservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {


    private LocationService locationService;

    public DriverController(LocationService locationService)
    {
        this.locationService=locationService;
    }

    @PostMapping("/location")
    public String updateDriverLocation(@RequestParam String driverId,
                                       @RequestParam double latitude,
                                       @RequestParam double longitude) {
        locationService.addDriverLocation(driverId, latitude, longitude);
        return "Location updated successfully!";
    }
}
