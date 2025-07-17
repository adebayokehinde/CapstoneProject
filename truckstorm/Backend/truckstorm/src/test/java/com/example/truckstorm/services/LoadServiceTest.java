package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.exceptions.InvalidLoadException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LoadServiceTest {
    @Mock
    private LoadServiceImpl loadService;
    @Mock
    private LoadRepository loadRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private ClientRepository clientRepository;

    private Driver testDriver;
    private Client testClient;


    LoadUploadRequest loadRequest;
    @BeforeEach
    public void setUp() {

        testDriver = new Driver();
        testDriver.setUserID(1);
        testDriver.setFirstName("John Driver");
        testDriver.setDriverStatus(DriverStatus.AVAILABLE);

        testClient = new Client();
        testClient.setUserID(1);
        testClient.setFirstName("Alice Client");
        testClient.setAccountStatus(AccountStatus.ACTIVE);

        loadRequest = new LoadUploadRequest();
        loadRequest.setPickupLocation("semicolon sabo yaba lagos");
        loadRequest.setDeliveryLocation("ozone sabo yaba lagos");
        loadRequest.setWeight(234.0);
        loadRequest.setLoadType(LoadType.GENERAL);
        loadRequest.setClientId(1);

    }
//    @AfterEach
//    public void tearDown() {
//        loadRepository.deleteAll();
//    }

    @Test
    void whenLoadIsPostedItCanBeSavedTest(){

         LoadPostResponse saveLoad = loadService.postLoad(loadRequest);
         //saveLoad.setLoadUpdated(false);
         saveLoad.setLoadStatus(LoadStatus.PENDING);
         saveLoad.setCreatedAt(LocalDateTime.of(2020, 02, 02, 02, 02));
         saveLoad.setLoadStatus(LoadStatus.PENDING);
         saveLoad.setPickupLocation("SABO YABA LAGOS");
         assertThat(saveLoad.getLoadUpdated()).isNotNull();
        assertThat(saveLoad.getLoadType()).isEqualTo(LoadType.GENERAL);
         assertThat(saveLoad.getLoadStatus()).isEqualTo(LoadStatus.PENDING);
        assertThat(loadRepository.count()).isEqualTo(1);

    }


//    @Test
//    void loadCanBeFoundByIdTest(){
//        Load savedLoad = loadRepository.save(
//                new Load("sabo yaba lagos", "oshodi oshodi lagos", 5500.0, "Fragile", "client101")
//        );
//
//        Load foundLoad = loadService.getLoadById(savedLoad.getId());
//
//        assertThat(foundLoad.getPickupLocation()).isEqualTo("sabo yaba lagos");
//        assertThat(foundLoad.getLoadType()).isEqualTo("Fragile");
//
//    }
//
//    @Test
//    void whenFindNonExistentLoad_thenThrowException() {
//        assertThrows(InvalidLoadException.class, () -> {
//            loadService.getLoadById(999L);
//        });
//    }
}
