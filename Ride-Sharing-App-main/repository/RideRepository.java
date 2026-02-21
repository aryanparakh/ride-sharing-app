package com.Aryan.rideshare.repository;

import com.Aryan.rideshare.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RideRepository extends MongoRepository<Ride, String> {
    Optional<Ride> findByStatus(String status);
    Optional<Ride> findByUserId(String userId);
}
