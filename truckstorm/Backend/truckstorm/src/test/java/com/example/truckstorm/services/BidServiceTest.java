package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.BidRequest;
import com.example.truckstorm.dtos.response.BidResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class BidServiceTest {
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
        loadRepository.save(load);

        request = new BidRequest();
        request.setPrice(new BigDecimal("1000.00"));
        request.setLoadId(1);
        request.setDriverId(3000);
        request.setLoadId(load.getId());
        request.setPickUpLocation(load.getPickupLocation());
        request.setLoadId(load.getId());


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
    void createBid_ValidRequest_ReturnsBidResponse() {

        BidResponse bidResponse = bidService.createBid(request);

        assertThat(bidRepository.findById(1)).isNotNull();
        assertThat(bidRepository.findById(bidResponse.getId())).isPresent();
        assertThat(bidRepository.count()).isEqualTo(1);
        assertEquals(3000, bidResponse.getDriverId());
        assertEquals("1000.00", bidResponse.getPrice().toString());
        assertEquals("PENDING", bidResponse.getBidStatus());

    }




}
