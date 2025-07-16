package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.DriverStatus;
import com.example.truckstorm.data.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByCurrentLocation(String currentLocation);
    List<Driver> findByCurrentLocationAndAvailable(String currentLocation, boolean available);
    List<Driver> findByAvailable(boolean available);
    List<Driver> findByDriverStatus(DriverStatus status);
}
