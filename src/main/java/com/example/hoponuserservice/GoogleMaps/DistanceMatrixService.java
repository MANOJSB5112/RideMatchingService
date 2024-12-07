package com.example.hoponuserservice.GoogleMaps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceMatrixService {

    @Value("${google.distance.matrix.url}")
    private String distanceMatrixUrl;

    @Value("${google.api.key}")
    private String apiKey;

    // Constants for fare calculation
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;
    public String getDistance(double originLat, double originLng, double destLat, double destLng) {
        RestTemplate restTemplate = new RestTemplate();

        // Construct the URL with latitudes and longitudes
        String origin = originLat + "," + originLng;
        String destination = destLat + "," + destLng;

        String url = String.format("%s?origins=%s&destinations=%s&mode=driving&key=%s",
                distanceMatrixUrl, origin, destination, apiKey);

        String response = restTemplate.getForObject(url, String.class);

        // Parse JSON response
        JsonNode rootNode = null;
        try {
            rootNode = new ObjectMapper().readTree(response);
            JsonNode element = rootNode
                    .path("rows")
                    .path(0)
                    .path("elements")
                    .path(0);

            // Extract distance and duration
            String distanceText = element.path("distance").path("text").asText();  // e.g., "3,944 km"
            String durationText = element.path("duration").path("text").asText();  // e.g., "39 hours 5 mins"

            // Extract the numerical distance in kilometers (remove ' km' from the text)
            String distanceKmStr = distanceText.replace(" km", "").replace(",", "");  // "3944" -> "3944 km"
            double distanceKm = Double.parseDouble(distanceKmStr);  // Convert to double

            // Extract the duration in minutes (if needed, you can add more logic to handle hours)
            String durationMinStr = durationText.replace(" hours", "").replace(" hour", "").replace(" mins", "").trim();
            String[] durationParts = durationMinStr.split(" ");
            double durationMinutes = 0;
            if (durationParts.length == 1) {
                durationMinutes = Double.parseDouble(durationParts[0]);  // In minutes
            } else if (durationParts.length == 2) {
                double hours = Double.parseDouble(durationParts[0]);
                double minutes = Double.parseDouble(durationParts[1]);
                durationMinutes = hours * 60 + minutes;  // Convert hours to minutes
            }

            // Calculate price
            double price = BASE_FARE + (distanceKm * PER_KM_RATE);

            // Calculate ETA (current time + duration in minutes)
            long etaMillis = System.currentTimeMillis() + (long)(durationMinutes * 60 * 1000);
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String eta = sdf.format(new java.util.Date(etaMillis));  // ETA in readable format

            // Return the details
            return String.format("Distance: %.2f km, Duration: %s, Price: $%.2f, ETA: %s",
                    distanceKm, durationText, price, eta);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calculating distance, duration, and price.";
        }
    }
}
