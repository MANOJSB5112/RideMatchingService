package com.example.hoponuserservice.DriverService.Service;

import com.example.hoponuserservice.Exceptions.DriverExceptions;
import com.example.hoponuserservice.model.Driver;
import org.springframework.web.bind.annotation.RequestParam;

public interface DiverService {

    Driver createNewDriver(String name,String email,String phoneNumber) throws DriverExceptions;
    void updateDriverLocation( String driverId,double latitude,double longitude);
    Driver updateDriverStatusAsAvailable(long driverId) throws DriverExceptions;
}
