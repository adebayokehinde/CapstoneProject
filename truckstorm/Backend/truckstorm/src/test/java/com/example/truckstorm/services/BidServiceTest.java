package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.BidRequest;
import com.example.truckstorm.dtos.response.BidResponse;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class BidServiceTest {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BidService bidService;


    BidRequest request;

    @BeforeEach
    public void setup() {

        Load load = new Load();
        load.setPickupLocation("semicolon sabo yaba lagos");
        load.setDeliveryLocation("ozone sabo yaba lagos");
        load.setWeight(234.0);
        load.setLoadType(LoadType.GENERAL);
        load.setClientId(1);
        load.setNote("note");
        loadRepository.save(load);

        request = new BidRequest();
//        request.setPrice(new BigDecimal("1000.00"));

        request.setClientId(load.getClientId());
        request.setLoadId(load.getId());
        request.setWeight(load.getWeight());
        request.setLoadId(load.getId());
        request.setPickUpLocation(load.getPickupLocation());
        request.setDestination(load.getDeliveryLocation());
        request.setLoadId(load.getId());
        request.setNote(load.getNote());


//        Driver driver = new Driver();
//        driver.setId(3000);
//        driverRepository.save(driver);
//        request.setDriverId(driver.getId());
//        BidResponse savedBid = new Bid();
//        savedBid.setId(1);
//        savedBid.setPrice(request.getPrice());
//        savedBid.setLoad(load);
//        savedBid.setDriver();
//        savedBid.setBidStatus(BidStatus.PENDING);

    }
    @AfterEach
    public  void tearDown() {
        bidRepository.deleteAll();
    }

    @Test
    void BidIsCreatedOnLoadCreation() {

        BidResponse bidResponse = bidService.createBid(request);

        assertThat(bidRepository.count()).isEqualTo(1);
        assertThat(loadRepository.count()).isEqualTo(1);
        Bid savedBid = bidRepository.findAll().get(0);
        assertThat(savedBid.getLoad()).isNotNull();
        assertEquals("PENDING", bidResponse.getBidStatus());

    }









}
