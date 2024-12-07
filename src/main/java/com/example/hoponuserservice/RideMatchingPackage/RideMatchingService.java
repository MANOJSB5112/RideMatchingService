package com.example.hoponuserservice.RideMatchingPackage;

import com.example.hoponuserservice.model.Rider;

public interface RideMatchingService {
    String matchNewRideRequest(Rider rider, double sourceLat, double sourceLong, double destLat, double destLong);
}
