package com.example.hoponuserservice.RedisGeo;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisLocationService {

    private static final String DRIVER_LOCATION_KEY = "driver_locations";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisLocationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addDriverLocation(String driverId, double latitude, double longitude) {
        redisTemplate.opsForGeo()
                .add(DRIVER_LOCATION_KEY, new Point(longitude, latitude), driverId);
    }

    public List<String> findNearestDrivers(double latitude, double longitude, double radiusInKm) {
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(DRIVER_LOCATION_KEY,
                        new Circle(new Point(longitude, latitude),
                                new Distance(radiusInKm, Metrics.KILOMETERS)));

        return results != null
                ? results.getContent()
                .stream()
                .map(result -> result.getContent().getName()) // Get driver ID (key name)
                .collect(Collectors.toList())
                : List.of(); // Return empty list if no results
    }
}
