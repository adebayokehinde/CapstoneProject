package com.example.truckstorm.controllers;

import com.example.truckstorm.services.LoadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Client")
public class ClientController {

    private final LoadService loadService;

    public ClientController(LoadService loadService) {
        this.loadService = loadService;
    }




}
