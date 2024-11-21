package com.example.hoponuserservice.repository;



import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.example.hoponuserservice.model.Rider;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@EnableScan
//public interface RiderRepo extends CrudRepository<Rider,String> {
//
//
//}

@EnableScan
@Repository
public interface RiderRepo extends DynamoDBCrudRepository<Rider, String> {
    Optional<Rider> findById(String id);

    Optional<Rider> findRidersByEmail(String email);
}