package com.example.hoponuserservice.RideMatchingPackage;

import com.example.hoponuserservice.Exceptions.DriverExceptions;
import com.example.hoponuserservice.Exceptions.GoogleApiExceptions;
import com.example.hoponuserservice.model.Ride;
import com.example.hoponuserservice.model.Rider;

public interface RideMatchingService {
    Ride matchNewRideRequest(Rider rider, double sourceLat, double sourceLong, double destLat, double destLong) throws GoogleApiExceptions, DriverExceptions;
}
