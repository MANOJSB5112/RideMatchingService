package com.example.hoponuserservice.DriverService.Controller;

import com.example.hoponuserservice.DriverService.Service.DiverService;
import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private DiverService driverService;

    @Autowired
    public DriverController(DiverService driverService)
    {
        this.driverService=driverService;
    }

    @PostMapping("/location")
    public String updateDriverLocation(@RequestParam String driverId,
                                       @RequestParam double latitude,
                                       @RequestParam double longitude) {
       driverService.updateDriverLocation(driverId,latitude,longitude);
        return "Location updated successfully for driverId"+driverId;
    }
}
