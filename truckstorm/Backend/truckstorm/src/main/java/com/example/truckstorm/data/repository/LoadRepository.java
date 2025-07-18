package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoadRepository extends JpaRepository<Load, Integer> {
//    Optional<Load> findById(int loadId);
//    List<Load> findByPickupLocation(String pickupLocation);
    List<Load> findByClientId(int clientId);
//    List<Load> findByAssignedDriverId(int driverId);
}
