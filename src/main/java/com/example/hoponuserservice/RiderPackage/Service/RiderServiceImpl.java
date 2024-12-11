package com.example.hoponuserservice.RiderPackage.Service;

import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.RideMatchingPackage.RideMatchingService;
import com.example.hoponuserservice.model.Rider;
import com.example.hoponuserservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService{
    private RiderRepository riderRepository;
    private RideMatchingService rideMatchingService;


    @Autowired
    public RiderServiceImpl(RiderRepository riderRepository,RideMatchingService rideMatchingService)
    {
        this.riderRepository=riderRepository;
        this.rideMatchingService=rideMatchingService;
    }

    @Override
    @Transactional
    public Rider createNewRider(String name, String email, String phoneNumber) throws RiderExceptions {
        if (name == null || email == null || phoneNumber == null) {
            throw new IllegalArgumentException("Name, email, and phone number must not be null");
        }

        Optional<Rider> riderOptional=riderRepository.findRiderByEmail(email);
        if (riderOptional.isPresent()) {
            throw new RiderExceptions("Rider with the given email address " + email + " already present");
        }

        Rider newRider = new Rider();
        newRider.setName(name);
        newRider.setEmail(email);
        newRider.setPhoneNumber(phoneNumber);

        System.out.println("Creating rider");
        Rider rider= riderRepository.save(newRider);
        System.out.println("Rider saved in db with the id "+rider.getId());
        return rider;
    }

    @Override
    public String matchNewRideRequest(Long riderId, double sourceLat, double sourceLong, double destLat, double destLong) throws RiderExceptions {
        Rider rider=validateAndGetRider(riderId);
        System.out.println("Ride request Processed......");
        String response=rideMatchingService.matchNewRideRequest(rider,sourceLat,sourceLong,destLat,destLong);
        return response;
    }

    public Rider validateAndGetRider(Long id) throws RiderExceptions {
        Optional<Rider> riderOptional=  riderRepository.findRiderById(id);
        if(riderOptional.isEmpty())
        {
            throw  new RiderExceptions("Rider with the Id "+id+" not found");
        }
        return riderOptional.get();
    }
}
