package com.example.hoponuserservice.controller;


import com.example.hoponuserservice.dtos.RideRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RideWebSocketController {

    @MessageMapping("/ride/request")  // Mapping for ride request endpoint
    @SendTo("/topic/ride/confirmation")  // Sending the response to this topic
    public RideRequest handleRideRequest(RideRequest rideRequest) {
        System.out.println("Received Ride Request: " + rideRequest);
        // Process and return the ride request for broadcasting
        return rideRequest;
    }

    @MessageMapping("/ride/errors")  // Mapping for error endpoint
    @SendTo("/topic/ride/errors")  // Sending errors to this topic
    public String handleError(Exception ex) {
        return "Error: " + ex.getMessage();
    }
}
