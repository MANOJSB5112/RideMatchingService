package com.example.hoponuserservice.repository;

import com.example.hoponuserservice.model.Rider;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableScan
@Repository
public interface RiderRepo extends CrudRepository<Rider,String> {
    @Override
    Optional<Rider> findById(String s);
    Optional<Rider> findRidersByEmail(String email);


}
