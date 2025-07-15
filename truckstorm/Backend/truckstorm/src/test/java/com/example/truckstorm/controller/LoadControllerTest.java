package com.example.truckstorm.controller;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.repository.LoadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class LoadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private LoadRepository loadRepository;

    @Test
    void whenClientPostLoad_thenCreateLoadTest() throws Exception {
        Load load = new Load();
        load.setPickupLocation("semicolon yaba Lagos");
        load.setDeliveryLocation("church Gbagada lagos");
        load.setWeight(7500.0);
        load.setLoadType("furniture");
        load.setClientId("client1");

        mockMvc.perform(post("/api/loads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(load)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pickupLocation").value("semicolon yaba Lagos"))
                .andExpect(jsonPath("$.deliveryLocation").value("church Gbagada lagos"));

        assertThat(loadRepository.count()).isEqualTo(1);
        Load savedLoad = loadRepository.findAll().get(0);
        assertThat(savedLoad.getClientId()).isEqualTo("client456");

    }
//    @Test
//    void whenGetLoadById_thenItReturnsLoadWithIdTest() throws Exception {
//        loadRepository.save(new Load("semicolon yaba lagos", "ozone yaba lagos ",  3000.0 ,"Perishable", "client1"));
//        loadRepository.save(new Load("semicolon yaba lagos", "ozone yaba lagos ",  4000.0 ,"furniture", "client2"));
//        loadRepository.save(new Load("semicolon yaba lagos", "ozone yaba lagos ",  6000.0 ,"laptops", "client3"));
//
//        mockMvc.perform(get("/api/loads"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("loads[0].clientId").value(3))
//                .andExpect(jsonPath("$[0].deliveryLocation").value("Orlando"))
//                .andExpect(jsonPath("$[1].weight").value(4000.0))
//                .andExpect(jsonPath("$[2].loadType").value("laptops"));
//
//    }
//    @Test
//    void whenPostInvalidLoad_ThenReturnBadRequest() throws Exception {
//        Load invalidLoad = new Load();
//        invalidLoad.setPickupLocation("");
//        invalidLoad.setWeight(-100.0);
//
//        mockMvc.perform(post("/api/loads")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidLoad)))
//                        .andExpect(status().isBadRequest())
//                        .andExpect(jsonPath("$.errors.length()").value(3));
//    }


}
