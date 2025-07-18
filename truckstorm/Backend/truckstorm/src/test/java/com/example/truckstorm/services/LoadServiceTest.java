package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.dtos.response.LoadUpdateResponse;
import com.example.truckstorm.exceptions.InvalidLoadException;
import com.example.truckstorm.exceptions.LoadNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest()
@RequiredArgsConstructor
public class LoadServiceTest {
    @Autowired
    private LoadServiceImpl loadService;
    @Autowired
    private LoadRepository loadRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
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
    @AfterEach
    public void tearDown() {
        loadRepository.deleteAll();

    }

    @Test
    void whenLoadIsPostedItCanBeSavedTest(){

        LoadPostResponse saveLoad = loadService.postLoad(loadRequest);
        assertThat(saveLoad.getLoadUpdated()).isNotNull();
        assertThat(loadRepository.findById(saveLoad.getPostResponseId())).isPresent();
        assertThat(loadRepository.count()).isEqualTo(1);

    }


    @Test
    void loadCanBeFoundByIdTest(){
        LoadPostResponse savedLoad = loadService.postLoad(loadRequest);
        LoadPostResponse foundLoad = loadService.getLoadById(savedLoad.getLoadId());
        assertThat(foundLoad).isNotNull();
        assertThat(foundLoad.getPickupLocation()).isEqualTo("semicolon sabo yaba lagos");
        assertThat(foundLoad.getLoadType()).isEqualTo(LoadType.GENERAL);

    }

    @Test
    void whenFindNonExistentLoad_thenThrowException() {
        assertThrows(LoadNotFoundException.class, () -> {
            loadService.getLoadById(999);
        });
    }
    @Test
    void whenRequestingAllLoads_thenReturnAllSavedLoads() {
        LoadUploadRequest secondRequest = new LoadUploadRequest();
        secondRequest.setPickupLocation("Another location");
        secondRequest.setDeliveryLocation("Different destination");
        secondRequest.setWeight(100.0);;
        secondRequest.setLoadType(LoadType.GENERAL);
        secondRequest.setClientId(1);

        loadService.postLoad(loadRequest);
        loadService.postLoad(secondRequest);


        List<LoadResponse> allLoads = loadService.getAllLoads();

        assertThat(allLoads).hasSize(2);
        assertThat(allLoads)
                .extracting("pickupLocation")
                .containsExactlyInAnyOrder(
                        "semicolon sabo yaba lagos",
                        "Another location"
                );
    }

    @Test
    public void whenLoadStatusIsUpdated_thenReturnUpdatedLoadStatus() {

        LoadPostResponse savedLoad = loadService.postLoad(loadRequest);

        LoadUpdateResponse updatedLoad = loadService.updateLoadStatus(savedLoad.getLoadId(), LoadStatus.ASSIGNED.toString());

        assertThat(updatedLoad).isNotNull();
        assertThat(updatedLoad.getId()).isEqualTo(savedLoad.getLoadId());
        assertThat(updatedLoad.getLoadStatus()).isEqualTo(LoadStatus.ASSIGNED);

        LoadPostResponse fetchedLoad = loadService.getLoadById(savedLoad.getLoadId());
//        assertThat(fetchedLoad.getLoadStatus()).isEqualTo(LoadStatus.ASSIGNED);
    }

}
