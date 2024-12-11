package com.example.hoponuserservice.GoogleMaps;

import com.example.hoponuserservice.Exceptions.GoogleApiExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gc")
public class DistanceMatrixController {
    @Value("${google.distance.matrix.url}")
    private String distanceMatrixUrl;

    @Value("${google.api.key}")
    private String apiKey;

    private DistanceMatrixService distanceMatrixService;

    @Autowired
    public DistanceMatrixController(  DistanceMatrixService distanceMatrixService) {
        this.distanceMatrixService=distanceMatrixService;

    }

    @GetMapping("/ride/eta")
    public String calculateEtaAndPrice(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) throws GoogleApiExceptions {
        return distanceMatrixService.calculateEtaAndPrice(startLat, startLng, endLat, endLng);
    }
}