package com.example.hoponuserservice.RiderPackage.Service;

import com.example.hoponuserservice.Exceptions.RiderAlreadyPresentException;
import com.example.hoponuserservice.model.Rider;

import java.util.List;

public interface RiderService {

    public Rider createNewRider(String name, String email, String phoneNumber) throws RiderAlreadyPresentException;
    List<Rider> getAllRiders();
}
