package com.example.hoponuserservice.RideMatchingPackage;

import com.example.hoponuserservice.Exceptions.DriverExceptions;
import com.example.hoponuserservice.Exceptions.GoogleApiExceptions;
import com.example.hoponuserservice.GoogleMaps.DistanceMatrixService;
import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import com.example.hoponuserservice.model.*;
import com.example.hoponuserservice.repository.DriverRepository;
import com.example.hoponuserservice.repository.LocationRepository;
import com.example.hoponuserservice.repository.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RideMatchingServiceImpl implements RideMatchingService{
    private RedisLocationService redisLocationService;
    private DriverRepository driverRepository;

    private DistanceMatrixService distanceMatrixService;

    private RideRepository rideRepository;
    private LocationRepository locationRepository;

    public RideMatchingServiceImpl(RedisLocationService redisLocationService,DriverRepository driverRepository,DistanceMatrixService distanceMatrixService,
                                   RideRepository rideRepository,LocationRepository locationRepository)
    {
        this.redisLocationService=redisLocationService;
        this.driverRepository=driverRepository;
        this.distanceMatrixService=distanceMatrixService;
        this.rideRepository=rideRepository;
        this.locationRepository=locationRepository;
    }
    @Override
    public Ride matchNewRideRequest(Rider rider, double sourceLat, double sourceLong, double destLat, double destLong) throws GoogleApiExceptions, DriverExceptions {
        List<String> nearestDriver=redisLocationService.findNearestDrivers(sourceLat,sourceLong,3.0);
        List<Long> driverIds=nearestDriver.stream()
                .map(Long::parseLong)
                .toList();

        Ride newRideRequest=new Ride();
        newRideRequest.setRider(rider);
        Location source=new Location(sourceLat,sourceLong);
        source=locationRepository.save(source);
        newRideRequest.setSource(source);
        Location destination=new Location(destLat,destLong);
        destination=locationRepository.save(destination);
        newRideRequest.setDestination(destination);
        Driver driver=null;
        for(long driverId:driverIds)
        {
            Optional<Driver> driverOptional=driverRepository.findById(driverId);
            if(driverOptional.isPresent())
            {
                 driver=driverOptional.get();
                if(driver.getDriverStatus().equals(DriverStatus.AVAILABLE)) {
                    System.out.println("Requesting ride with the driver Id "+driverId);
                       //we need to add some logic here to send notification to drivers to accept the ride request
                      newRideRequest.setDriver(driver);
                    System.out.println("Matched ride with the driver Id "+driverId);
                    break;
                }
            }
        }
        if(newRideRequest.getDriver()==null)
        {
            throw new DriverExceptions("Ride Request could not complete as drivers not found");
        }

        String etaAndPrice=distanceMatrixService.calculateEtaAndPrice(sourceLat,sourceLong,destLat,destLong);
        String[] strArray=etaAndPrice.split(",");
        double distance=Double.parseDouble(strArray[0]);
        newRideRequest.setDistance(distance);
        newRideRequest.setDuration(strArray[1]);
        double fare=Double.parseDouble(strArray[2]);
        newRideRequest.setFare(fare);
        newRideRequest.setEta(strArray[3]);

        assert driver != null;
        driver.setDriverStatus(DriverStatus.BUSY);
        driverRepository.save(driver);
        newRideRequest.setStatus(RideStatus.ACCEPTED);
        return rideRepository.save(newRideRequest);

    }
}
