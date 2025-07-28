package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import com.example.truckstorm.dtos.request.DriverRequest;
import com.example.truckstorm.dtos.response.DriverResponse;
import com.example.truckstorm.dtos.response.DriverUpdateResponse;
import com.example.truckstorm.services.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriversController {

    private final DriverService driverService;

    public DriversController(DriverService driverService) {
        this.driverService = driverService;
    }


    @PostMapping("/registerDriver")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody DriverRegistrationRequest driver) {
        try {
            DriverResponse registeredDriver = driverService.registerDriver(driver);
            return new ResponseEntity<>(registeredDriver, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<?> updateDriverAvailability(
            @PathVariable int id,
            @RequestParam boolean available){
        try {
            DriverUpdateResponse updatedDriver = driverService.updateDriverAvailability(id, available);
            return ResponseEntity.ok(updatedDriver);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDriversInRegion(
            @RequestParam String region) {
            List<Driver> drivers = driverService.findAvailableDriversInRegion(region);
            return ResponseEntity.ok(drivers);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable int id) {
        Driver driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

}
