package com.example.hoponuserservice.controller;

import com.example.hoponuserservice.dtos.LocationUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {

    private final SimpMessagingTemplate messagingTemplate;

    public LocationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/location/update")
    public void updateLocation(LocationUpdateRequestDto request) {
        messagingTemplate.convertAndSend("/topic/location/" + request.getRiderId(), request);
    }
}