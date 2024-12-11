package com.example.hoponuserservice.repository;

import com.example.hoponuserservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findDriverByEmail(String email);

    @Override
    Optional<Driver> findById(Long aLong);
}