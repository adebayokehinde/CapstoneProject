package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.services.BidService;
import com.example.truckstorm.services.LoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bidding")
public class BiddingController {
    private final BidService bidService;
    private final LoadService loadService;

    public BiddingController(BidService bidService, LoadService loadService) {
        this.bidService = bidService;
        this.loadService = loadService;
    }

    @GetMapping("/load/{loadId}/drivers")
    public ResponseEntity<List<Driver>> findCompatibleDriversForLoad(@PathVariable Long loadId) {

        Load load = loadService.getLoadById(loadId);
        List<Driver> drivers = bidService.findCompatibleDriversForLoad(load);
        return ResponseEntity.ok(drivers);
    }

    @PostMapping("/assign")
    public ResponseEntity<Driver> assignDriverToLoad(
            @RequestParam Long loadId,
            @RequestParam Long driverId) {
        Driver driver = bidService.assignDriverToLoad(loadId, driverId);
        return ResponseEntity.ok(driver);
    }
}
