package com.example.hoponuserservice.DriverService.Service;

import com.example.hoponuserservice.RedisGeo.RedisLocationService;
import org.springframework.stereotype.Service;

@Service
public class DiverServiceImpl implements DiverService{
    private RedisLocationService redisLocationService;

    public DiverServiceImpl(RedisLocationService redisLocationService)
    {
        this.redisLocationService = redisLocationService;
    }
    @Override
    public void updateDriverLocation(String driverId, double latitude, double longitude) {
        redisLocationService.addDriverLocation(driverId, latitude, longitude);
    }
}
