package com.example.hoponuserservice.AWSLocationService.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.location.LocationClient;
import software.amazon.awssdk.services.location.model.CalculateRouteRequest;
import software.amazon.awssdk.services.location.model.CalculateRouteResponse;
import software.amazon.awssdk.services.location.LocationClient;
import software.amazon.awssdk.services.location.model.*;
import software.amazon.awssdk.regions.Region;

@Service
public class AmazonLocationService {

    @Value("${aws.location.routeCalculatorName}")
    private String routeCalculatorName;

    private final LocationClient locationClient;

    public AmazonLocationService(LocationClient locationClient) {
        this.locationClient = locationClient;
    }

    public CalculateRouteResponse calculateRoute(double startLat, double startLng, double endLat, double endLng) {
        CalculateRouteRequest request = CalculateRouteRequest.builder()
                .calculatorName(routeCalculatorName)
                .departurePosition(startLng, startLat) // Longitude first as per AWS API requirement
                .destinationPosition(endLng, endLat) // Longitude first as per AWS API requirement
                .travelMode(TravelMode.CAR)
                .build();

        return locationClient.calculateRoute(request);
    }
}