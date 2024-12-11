package com.example.hoponuserservice.DriverService.Controller;

import com.example.hoponuserservice.DriverService.Service.DiverService;
import com.example.hoponuserservice.Exceptions.DriverExceptions;
import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import com.example.hoponuserservice.dtos.*;
import com.example.hoponuserservice.dtos.ResponseStatus;
import com.example.hoponuserservice.model.Driver;
import com.example.hoponuserservice.model.Rider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<DriverCreationResponseDto> createNewDriver(@Valid @RequestBody DriverCreationRequestDto driverCreationRequestDto) throws DriverExceptions {
       Driver driver = driverService.createNewDriver(
                driverCreationRequestDto.getName(),
                driverCreationRequestDto.getEmail(),
                driverCreationRequestDto.getPhoneNumber()
        );

        DriverCreationResponseDto responseDto = new DriverCreationResponseDto();
        responseDto.setDriver(driver);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/location")
    public String updateDriverLocation(@RequestParam String driverId,
                                       @RequestParam double latitude,
                                       @RequestParam double longitude) {
       driverService.updateDriverLocation(driverId,latitude,longitude);
        return "Location updated successfully for driverId"+driverId;
    }

    @PutMapping("/status")
    public Driver updateDriverStatusAsAvailable(@RequestParam long driverId) throws DriverExceptions {
        return driverService.updateDriverStatusAsAvailable(driverId);
    }
}
