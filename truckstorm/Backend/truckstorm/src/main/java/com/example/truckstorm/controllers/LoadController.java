package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.dtos.response.LoadUpdateResponse;
import com.example.truckstorm.services.LoadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/load")
public class LoadController {

    private final LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping
    public ResponseEntity<?> createLoad(@RequestBody LoadUploadRequest loadUploadRequest) {
        LoadPostResponse response = loadService.postLoad(loadUploadRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLoadById(@PathVariable int id) {
        LoadPostResponse loadPostResponse = loadService.getLoadById(id);
        return ResponseEntity.ok(loadPostResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoads() {
        List<LoadResponse> loads = loadService.getAllLoads();
        return ResponseEntity.ok(loads);
    }









    @GetMapping("/status/{status}")
    public ResponseEntity<?> getLoadsByStatus(@PathVariable LoadStatus status) {
        List<LoadResponse> loads = loadService.getAllLoads();
        return ResponseEntity.ok(loads);
    }



    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getLoadsByClientId(@PathVariable int clientId) {
        List<LoadResponse> loads = loadService.getLoadsByClientId(clientId);
        return ResponseEntity.ok(loads);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateLoadStatus(
            @PathVariable int id,
            @RequestParam String status) {
        LoadUpdateResponse updatedLoad = loadService.updateLoadStatus(id, status);
        return ResponseEntity.ok(updatedLoad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoad(@PathVariable int id) {
        loadService.deleteLoad(id);
        return ResponseEntity.noContent().build();
    }










}
