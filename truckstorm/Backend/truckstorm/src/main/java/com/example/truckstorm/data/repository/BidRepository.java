package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByDriverId(Long driverId);
    List<Bid> findByLoadId(Long loadId);
    List<Bid> findByClientId(Long clientId);

}
