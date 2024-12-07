package com.example.hoponuserservice.DriverService.Service;

import org.springframework.web.bind.annotation.RequestParam;

public interface DiverService {
    void updateDriverLocation( String driverId,double latitude,double longitude);
}
