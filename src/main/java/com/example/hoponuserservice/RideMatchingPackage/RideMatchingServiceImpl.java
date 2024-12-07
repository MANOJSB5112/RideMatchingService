package com.example.hoponuserservice.RideMatchingPackage;

import com.example.hoponuserservice.model.Rider;
import org.springframework.stereotype.Service;

@Service
public class RideMatchingServiceImpl implements RideMatchingService{
    @Override
    public String matchNewRideRequest(Rider rider, double sourceLat, double sourceLong, double destLat, double destLong) {
        return null;
    }
}
