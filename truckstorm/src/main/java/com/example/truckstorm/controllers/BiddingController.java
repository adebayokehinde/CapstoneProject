package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.services.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bidding")
public class BiddingController {
////    private final BidService BiddingService;
//
////    public BiddingController(BidService BiddingService) {
////        this.BiddingService = BiddingService;
////    }
//
//    @GetMapping("/load/{loadId}/drivers")
//    public ResponseEntity<List<Driver>> findCompatibleDriversForLoad(@PathVariable Long loadId) {
//
//        Load load = new Load();
//        load.setId(loadId);
//
//        List<Driver> drivers = BiddingService.findCompatibleDriversForLoad(load);
//        return ResponseEntity.ok(drivers);
//    }
//
//    @PostMapping("/assign")
//    public ResponseEntity<Driver> assignDriverToLoad(
//            @RequestParam Long loadId,
//            @RequestParam Long driverId) {
//        Driver driver = BiddingService.assignDriverToLoad(loadId, driverId);
//        return ResponseEntity.ok(driver);
//    }
}
