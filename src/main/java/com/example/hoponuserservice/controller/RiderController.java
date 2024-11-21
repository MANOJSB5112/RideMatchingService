package com.example.hoponuserservice.controller;

import com.example.hoponuserservice.Exceptions.RiderAlreadyPresentException;
import com.example.hoponuserservice.dtos.ResponseStatus;
import com.example.hoponuserservice.dtos.RiderCreationRequestDto;
import com.example.hoponuserservice.dtos.RiderCreationResponseDto;
import com.example.hoponuserservice.model.Rider;
import com.example.hoponuserservice.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RiderController {
    private RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService)
    {
        this.riderService=riderService;
    }

    @PostMapping("/rider")
    public ResponseEntity<RiderCreationResponseDto> createNewRider(@RequestBody RiderCreationRequestDto riderCreationRequestDto) throws RiderAlreadyPresentException {
        String name=riderCreationRequestDto.getName();
        String email=riderCreationRequestDto.getEmail();
        String phoneNumber=riderCreationRequestDto.getPhoneNumber();
        double latitude=riderCreationRequestDto.getLatitude();
        double longitude=riderCreationRequestDto.getLongitude();

        Rider rider=riderService.createNewRider(name,email,phoneNumber,latitude,longitude);

        RiderCreationResponseDto responseDto=new RiderCreationResponseDto();
        responseDto.setRider(rider);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
