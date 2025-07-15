package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LoadServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Driver testDriver;
    private Client testClient;

    @BeforeEach
    void setUp() {
        testDriver = new Driver();
        testDriver.setUserID("driver1");
        testDriver.setName("John Driver");
        testDriver.setDriverStatus(DriverStatus.AVAILABLE);

        testClient = new Client();
        testClient.setUserID("client1");
        testClient.setName("Alice Client");
        testClient.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Test
    void getDriverById_ShouldReturnDriver() {
        when(driverRepository.findById("driver1")).thenReturn(Optional.of(testDriver));

        User result = userService.getUserById("driver1");

        assertNotNull(result);
        assertEquals("driver1", result.getUserID());
        assertEquals("John Driver", result.getName());
        assertTrue(result instanceof Driver);
    }

    @Test
    void getClientById_ShouldReturnClient() {
        when(clientRepository.findById("client1")).thenReturn(Optional.of(testClient));

        User result = userService.getUserById("client1");

        assertNotNull(result);
        assertEquals("client1", result.getUserID());
        assertEquals("Alice Client", result.getName());
        assertTrue(result instanceof Client);
    }

    @Test
    void getUserById_NotFound_ShouldThrowException() {
        when(driverRepository.findById("unknown")).thenReturn(Optional.empty());
        when(clientRepository.findById("unknown")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById("unknown"));
    }

    @Test
    void updateDriverProfile_ShouldReturnUpdatedDriver() {
        Driver updatedDriver = new Driver();
        updatedDriver.setName("Updated Name");

        when(driverRepository.findById("driver1")).thenReturn(Optional.of(testDriver));
        when(driverRepository.save(any(Driver.class))).thenReturn(updatedDriver);

        User result = userService.updateUserProfile("driver1", updatedDriver);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(driverRepository).save(any(Driver.class));
    }

    @Test
    void deleteDriver_ShouldCallRepository() {
        when(driverRepository.existsById("driver1")).thenReturn(true);

        userService.deleteUser("driver1");

        verify(driverRepository).deleteById("driver1");
    }
}
