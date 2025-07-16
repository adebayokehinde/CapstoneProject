package com.example.truckstorm.servicestest;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.exceptions.InvalidLoadException;
import com.example.truckstorm.services.LoadServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class LoadServiceTest {

}
