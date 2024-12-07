package com.example.hoponuserservice.RiderPackage.Controller;

import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.dtos.*;
import com.example.hoponuserservice.dtos.ResponseStatus;
import com.example.hoponuserservice.model.Rider;
import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import com.example.hoponuserservice.RiderPackage.Service.RiderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rider")
public class RiderController {

    private final RiderService riderService;
    private RedisLocationService redisLocationService;

    @Autowired
    public RiderController(RiderService riderService, RedisLocationService redisLocationService) {
        this.riderService = riderService;
        this.redisLocationService = redisLocationService;
    }

    @PostMapping("/new")
    public ResponseEntity<RiderCreationResponseDto> createNewRider(@Valid @RequestBody RiderCreationRequestDto riderCreationRequestDto) throws RiderExceptions {
        Rider rider = riderService.createNewRider(
                riderCreationRequestDto.getName(),
                riderCreationRequestDto.getEmail(),
                riderCreationRequestDto.getPhoneNumber()
        );

        RiderCreationResponseDto responseDto = new RiderCreationResponseDto();
        responseDto.setRider(rider);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/nearest-drivers")
    public List<String> getNearestDrivers(@RequestParam double latitude,
                                          @RequestParam double longitude,
                                          @RequestParam double radiusInKm) {
        return redisLocationService.findNearestDrivers(latitude, longitude, radiusInKm);
    }

    @PostMapping("/ride-request")
    public void requestNewRide(RideRequestDto rideRequestDto) throws RiderExceptions {
        long riderId=rideRequestDto.getRiderId();
        double sourceLat=rideRequestDto.getSourceLat();
        double sourceLong=rideRequestDto.getSourceLong();
        double destLat=rideRequestDto.getDestLat();
        double destLong=rideRequestDto.getDestLong();


        String response=riderService.matchNewRideRequest(riderId,sourceLat,sourceLong,destLat,destLong);

    }
}
