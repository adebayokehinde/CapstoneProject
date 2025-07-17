package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final LoadService loadService;
    @Autowired
    public ClientController(LoadService loadService) {
        this.loadService = loadService;
    }

    @GetMapping("/{clientId}/loads")
    public ResponseEntity<?> getClientsLoads(@PathVariable int clientId) {
        List<LoadResponse> loads = loadService.getLoadsByClientId(clientId);
        return ResponseEntity.ok(loads);
    }



}
