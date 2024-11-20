package com.example.hoponuserservice.repository;

import com.example.hoponuserservice.model.Rider;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface RiderRepo extends CrudRepository<Rider,String> {
    @Override
    Optional<Rider> findById(String s);
}
