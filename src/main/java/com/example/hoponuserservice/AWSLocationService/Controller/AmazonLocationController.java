package com.example.hoponuserservice.AWSLocationService.Controller;

import com.example.hoponuserservice.AWSLocationService.Service.AmazonLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.location.model.CalculateRouteResponse;


@RestController
@RequestMapping("/aws")
public class AmazonLocationController {


    private final AmazonLocationService amazonLocationService;

    public AmazonLocationController(AmazonLocationService amazonLocationService) {
        this.amazonLocationService = amazonLocationService;
    }

    @GetMapping("/ride/eta")
    public String calculateEtaAndPrice(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) {

        // Call the service with latitude and longitude
        CalculateRouteResponse response = amazonLocationService.calculateRoute(startLat, startLng, endLat, endLng);

        // Extract distance and duration
//        double distance = response.legs().stream()
//                .mapToDouble(leg -> leg.distance())
//                .sum()/1000.0;
//        double duration = response.legs().stream()
//                .mapToDouble(leg -> leg.durationSeconds())
//                .sum()/60.0;

        // Extract distance and duration
        double distanceKm = response.summary().distance();
        double durationMinutes =  Math.ceil(response.summary().durationSeconds() / 60.0);


        // Pricing logic
        double baseFare = 50.0;
        double perKmRate = 10.0;
        double price = baseFare + (distanceKm * perKmRate);

        return String.format("Distance: %.2f km, Duration: %.2f minutes, Price: %.2f",
                distanceKm, durationMinutes, price);
    }
}

// Validate input
//validateCoordinates(startLat, startLng, endLat, endLng);

// Call the service with latitude and longitude
//CalculateRouteResponse response = amazonLocationService.calculateRoute(startLat, startLng, endLat, endLng);
//
//// Extract distance and duration
//double distanceKm = response.legs().stream()
//        .mapToDouble(leg -> leg.distance())
//        .sum() / 1000.0; // Convert meters to kilometers
//int durationMinutes = (int) Math.ceil(response.legs().stream()
//        .mapToDouble(leg -> leg.durationSeconds())
//        .sum() / 60.0); // Convert seconds to minutes
//
//// Pricing logic
//double baseFare = 50.0;
//double perKmRate = 10.0;
//double price = baseFare + (distanceKm * perKmRate);
//
//RideEstimate estimate = new RideEstimate(
//        Math.round(distanceKm * 100.0) / 100.0, // Round to 2 decimal places
//        durationMinutes,
//        Math.round(price * 100.0) / 100.0
//);