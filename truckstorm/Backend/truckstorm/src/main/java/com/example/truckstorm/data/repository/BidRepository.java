package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    @Query("SELECT b FROM Bid b WHERE b.driver.userID = :driverId")
    List<Bid> findByDriverId(@Param("driverId") int driverId);
//    List<Bid> findByLoadId(int loadId);
//    List<Bid> findByClientId(int clientId);

}
