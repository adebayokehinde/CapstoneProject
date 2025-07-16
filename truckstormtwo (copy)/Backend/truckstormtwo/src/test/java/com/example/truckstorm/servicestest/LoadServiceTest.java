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
    @Autowired
    private LoadServiceImpl loadService;
    @Autowired
    private LoadRepository loadRepository;

    @Test
    void whenLoadIsPostedItCanBeSavedTest(){
        Load load = new Load("semicolon yaba lagos", "navy town apapa, lagos", 10000.0, "house equipmant", "client1");
        Load saveLoad = loadService.postLoad(load);

        assertThat(saveLoad.getId()).isNotNull();
        assertThat(loadRepository.findById(saveLoad.getId())).isPresent();
        assertThat(loadRepository.count()).isEqualTo(1);
    }
    @Test
    void loadCanBeFoundByIdTest(){
        Load savedLoad = loadRepository.save(
                new Load("sabo yaba lagos", "oshodi oshodi lagos", 5500.0, "Fragile", "client101")
        );

        Load foundLoad = loadService.getLoadById(savedLoad.getId());

        assertThat(foundLoad.getPickupLocation()).isEqualTo("sabo yaba lagos");
        assertThat(foundLoad.getLoadType()).isEqualTo("Fragile");

    }

    @Test
    void whenFindNonExistentLoad_thenThrowException() {
        assertThrows(InvalidLoadException.class, () -> {
            loadService.getLoadById(999L);
        });
    }
}
