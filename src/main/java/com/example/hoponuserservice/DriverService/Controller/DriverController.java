package com.example.hoponuserservice.DriverService.Controller;

import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private RedisLocationService redisLocationService;

    public DriverController(RedisLocationService redisLocationService)
    {
        this.redisLocationService = redisLocationService;
    }

    @PostMapping("/location")
    public String updateDriverLocation(@RequestParam String driverId,
                                       @RequestParam double latitude,
                                       @RequestParam double longitude) {
        redisLocationService.addDriverLocation(driverId, latitude, longitude);
        return "Location updated successfully!";
    }
}
