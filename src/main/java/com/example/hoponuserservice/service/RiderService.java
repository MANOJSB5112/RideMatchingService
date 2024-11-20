package com.example.hoponuserservice.service;

import com.example.hoponuserservice.Exceptions.RiderAlreadyPresentException;
import com.example.hoponuserservice.model.Rider;

public interface RiderService {

    public Rider createNewRider(String name, String email, String phoneNumber, double latitude, double longitude) throws RiderAlreadyPresentException;
}
