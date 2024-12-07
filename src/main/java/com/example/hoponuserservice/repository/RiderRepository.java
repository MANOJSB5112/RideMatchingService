package com.example.hoponuserservice.repository;


import com.example.hoponuserservice.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

  Optional<Rider> findRiderByEmail(String email);
  Rider save(Rider rider);
}