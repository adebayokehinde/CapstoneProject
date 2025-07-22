package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {
    Truck findByTruckLicensedPlateNumber(String licensePlate);
}
