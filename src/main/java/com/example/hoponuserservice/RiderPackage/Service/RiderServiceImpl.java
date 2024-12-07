package com.example.hoponuserservice.RiderPackage.Service;

import com.example.hoponuserservice.Exceptions.RiderAlreadyPresentException;
import com.example.hoponuserservice.RiderPackage.Service.RiderService;
import com.example.hoponuserservice.model.Location;
import com.example.hoponuserservice.model.Rider;

import com.example.hoponuserservice.repository.RiderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService {
    private final RiderRepo riderRepo;

    @Autowired
    public RiderServiceImpl(RiderRepo riderRepo) {
        this.riderRepo = riderRepo;
    }

    @Override
    @Transactional
    public Rider createNewRider(String name, String email, String phoneNumber, double latitude, double longitude) throws RiderAlreadyPresentException {
        if (name == null || email == null || phoneNumber == null) {
            throw new IllegalArgumentException("Name, email, and phone number must not be null");
        }

        Optional<Rider> riderOptional = riderRepo.findRidersByEmail(email);
        if (riderOptional.isPresent()) {
            throw new RiderAlreadyPresentException("Rider with the given email address " + email + " already present");
        }

        Rider newRider = new Rider();
        newRider.setName(name);
        newRider.setEmail(email);
        newRider.setPhoneNumber(phoneNumber);

        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        newRider.setLocation(location);
        System.out.println("Creating rider");
        Rider rider= riderRepo.save(newRider);
        System.out.println("User saved: " + rider);
        return rider;
    }

    @Override
    public List<Rider> getAllRiders() {
        return riderRepo.findAll();

    }
}
