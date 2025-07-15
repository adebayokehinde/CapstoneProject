package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<Load, Long> {
    List<Load> findByPickupLocation(String pickupLocation);
    List<Load> findByStatus(Load.LoadStatus status);
    List<Load> findByClientId(String clientId);
}
