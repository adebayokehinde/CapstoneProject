package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Client;
import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.User;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceIntegration {

}
