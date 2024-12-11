package com.example.hoponuserservice.model;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Location extends BaseModel {
    private double latitude;
    private double longitude;

    public Location(double sourceLat, double sourceLong) {
        super();
    }

    public Location() {

    }
}

