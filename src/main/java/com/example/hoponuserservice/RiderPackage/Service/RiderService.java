package com.example.hoponuserservice.RiderPackage.Service;

import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.model.Rider;

public interface RiderService {

    public Rider createNewRider(String name, String email, String phoneNumber) throws RiderExceptions;
    String matchNewRideRequest(Long riderId,double sourceLat,double sourceLong,double destLat,double destLong) throws RiderExceptions;
}
