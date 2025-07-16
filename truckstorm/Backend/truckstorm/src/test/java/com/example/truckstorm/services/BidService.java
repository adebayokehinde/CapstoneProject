package com.example.truckstorm.services;


import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BidService {
    @Mock
    private DriverRepository driverRepository;

    @Mock
    private LoadRepository loadRepository;

    @InjectMocks
    private MatchingServiceImpl matchingService;

    private Load testLoad;
    private Driver compatibleDriver;
    private Driver incompatibleDriver;

    @BeforeEach
    void setUp() {

        testLoad = new Load();
        testLoad.setType("load1");
        testLoad.setWeight(5000.0);
        testLoad.setPickupLocation(new Location("New York", 40.7128, -74.0060));


        compatibleDriver = new Driver();
        compatibleDriver.setUserID("driver1");
        compatibleDriver.setCurrentLocation("New York");
        compatibleDriver.setMaxLoadCapacity(10000.0);
        compatibleDriver.setDriverStatus(DriverStatus.AVAILABLE);


        incompatibleDriver = new Driver();
        incompatibleDriver.setUserID("driver2");
        incompatibleDriver.setCurrentLocation("Chicago");
        incompatibleDriver.setMaxLoadCapacity(3000.0);
        incompatibleDriver.setDriverStatus(DriverStatus.AVAILABLE);
    }

    @Test
    void findCompatibleDrivers_ShouldReturnOnlyCompatibleDrivers() {
        when(loadRepository.findById("load1")).thenReturn(Optional.of(testLoad));
        when(driverRepository.findByDriverStatus(DriverStatus.AVAILABLE))
                .thenReturn(Arrays.asList(compatibleDriver, incompatibleDriver));

        List<Driver> results = matchingService.findCompatibleDrivers(testLoad);

        assertEquals(1, results.size());
        assertEquals("driver1", results.get(0).getUserID());
    }

    @Test
    void matchLoadToDriver_ShouldAssignDriverAndUpdateStatus() {
        when(loadRepository.findById(1L)).thenReturn(Optional.of(testLoad));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(compatibleDriver));
        when(loadRepository.save(any(Load.class))).thenReturn(testLoad);

        Load result = matchingService.matchLoadToDriver("load1", "driver1");

        assertNotNull(result);
        assertEquals(compatibleDriver, result.getAssignedDriver());
        assertEquals(LoadStatus.ASSIGNED, result.getLoadStatus());
        verify(loadRepository).save(testLoad);
    }

    @Test
    void matchLoadToDriver_WithIncompatibleDriver_ShouldThrowException() {
        when(loadRepository.findById("load1")).thenReturn(Optional.of(testLoad));
        when(driverRepository.findById("driver2")).thenReturn(Optional.of(incompatibleDriver));

        assertThrows(IllegalArgumentException.class, () -> {
            matchingService.matchLoadToDriver("load1", "driver2");
        });
    }
}
