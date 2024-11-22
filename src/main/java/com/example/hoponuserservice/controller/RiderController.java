package com.example.hoponuserservice.controller;

import com.example.hoponuserservice.Exceptions.RiderAlreadyPresentException;
import com.example.hoponuserservice.dtos.ResponseStatus;
import com.example.hoponuserservice.dtos.RideRequest;
import com.example.hoponuserservice.dtos.RiderCreationRequestDto;
import com.example.hoponuserservice.dtos.RiderCreationResponseDto;
import com.example.hoponuserservice.model.Rider;
import com.example.hoponuserservice.service.LocationService;
import com.example.hoponuserservice.service.RiderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rider")
public class RiderController {

    private final RiderService riderService;
    private LocationService locationService;

    @Autowired
    public RiderController(RiderService riderService,LocationService locationService) {
        this.riderService = riderService;
        this.locationService=locationService;
    }

    @PostMapping("/new")
    public ResponseEntity<RiderCreationResponseDto> createNewRider(@Valid @RequestBody RiderCreationRequestDto riderCreationRequestDto) throws RiderAlreadyPresentException {
        Rider rider = riderService.createNewRider(
                riderCreationRequestDto.getName(),
                riderCreationRequestDto.getEmail(),
                riderCreationRequestDto.getPhoneNumber(),
                riderCreationRequestDto.getLatitude(),
                riderCreationRequestDto.getLongitude()
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
        return locationService.findNearestDrivers(latitude, longitude, radiusInKm);
    }
}
