package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Bid;
import com.example.truckstorm.data.models.BidStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
    @Query("SELECT b FROM Bid b WHERE b.driver.userID = :driverId")
    List<Bid> findByDriverId(@Param("driverId") int driverId);

    @Query("SELECT b FROM Bid b WHERE b.client.userID = :clientId")
    List<Bid> findByClientId(@Param("clientId") int clientId);

    List<Bid> findByLoadId(int loadId);
    List<Bid> findAllByBidStatus(BidStatus bidStatus);
}
