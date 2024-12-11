package com.example.hoponuserservice.DriverService.Service;

import com.example.hoponuserservice.Exceptions.DriverExceptions;
import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import com.example.hoponuserservice.model.Driver;
import com.example.hoponuserservice.model.DriverStatus;
import com.example.hoponuserservice.model.Rider;
import com.example.hoponuserservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiverServiceImpl implements DiverService{
    private RedisLocationService redisLocationService;
    private DriverRepository driverRepository;

    @Autowired
    public DiverServiceImpl(RedisLocationService redisLocationService,DriverRepository driverRepository)
    {
        this.redisLocationService = redisLocationService;
        this.driverRepository=driverRepository;
    }

    @Override
    public Driver createNewDriver(String name, String email, String phoneNumber) throws DriverExceptions {
        if (name == null || email == null || phoneNumber == null) {
            throw new IllegalArgumentException("Name, email, and phone number must not be null");
        }

       Optional<Driver> driverOptional=driverRepository.findDriverByEmail(email);
        if (driverOptional.isPresent()) {
            throw new DriverExceptions("Driver with the given email address " + email + " already present");
        }

        Driver newDriver=new Driver();
        newDriver.setName(name);
        newDriver.setEmail(email);
        newDriver.setPhoneNumber(phoneNumber);

        System.out.println("Creating driver");
        Driver driver= driverRepository.save(newDriver);
        System.out.println("Driver saved in db with the id "+driver.getId());
        return driver;
    }

    @Override
    public void updateDriverLocation(String driverId, double latitude, double longitude) {
        redisLocationService.addDriverLocation(driverId, latitude, longitude);
    }

    @Override
    public Driver updateDriverStatusAsAvailable(long driverId) throws DriverExceptions {
        Optional<Driver>  driverOptional=driverRepository.findById(driverId);
        if(driverOptional.isEmpty())
        {
            throw new DriverExceptions("Driver not  found with the Id "+driverId+" for the updating as available ");
        }
        Driver driver=driverOptional.get();
        driver.setDriverStatus(DriverStatus.AVAILABLE);
        return driverRepository.save(driver);
    }
}
