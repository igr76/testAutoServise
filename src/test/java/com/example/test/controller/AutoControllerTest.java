package com.example.test.controller;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.dto.USD;
import com.example.test.entity.Auto;
import com.example.test.entity.Driver;
import com.example.test.mapper.AutoMapper;
import com.example.test.repository.AutoRepository;
import com.example.test.service.impl.ServiceAutoImpl;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(DriverController.class)
public class AutoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private  AutoController autoController;
    @SpyBean
    private ServiceAutoImpl serviceAuto;
    @MockBean
    private AutoRepository autoRepository;
    @MockBean
    private AutoMapper autoMapper;
    private Auto auto;
    private AutoDto autoDto;
    private     List<String> autos = new ArrayList<>();
    private List<Auto> autoList = new ArrayList<>();
    private List<AutoDto> autoDtoList = new ArrayList<>();
    private String number;
    @BeforeEach
    void init() {
        number = "A000AA125";
        auto = new Auto("A000AA125",1234567,"TOYOTA","CERES",2007,autos,null);
        autoDto = new AutoDto("A000AA125",1234567,"TOYOTA","CERES",2007,null);
        autoList.add(auto); autoDtoList.add(autoDto);
    }
    @Test
    void getAuto() throws  Exception {
        String url = "/auto/" + number;
        when(autoRepository.findById(any())).thenReturn(Optional.ofNullable(auto));
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getAllDriver() throws  Exception {
        String url = "/auto";
        when(autoRepository.findAll()).thenReturn(autoList);
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void addDriver() throws  Exception{
        String url = "/auto" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("number","A000AA125");
        driverDTO.put("VIN","1234567");
        driverDTO.put("manufacturer","TOYOTA");

        when(autoRepository.existsById(any())).thenReturn(false);
        mockMvc.perform(get(url)
                        .param("number", "A000AA125")
                        .param("VIN", "1234567")
                        .param("manufacturer","TOYOTA")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
    @Test
    void updateDriver() throws  Exception{
        String url = "/auto" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("number","A001AA125");
        driverDTO.put("VIN","1234567");
        driverDTO.put("manufacturer","TOYOTA");

        when(autoRepository.existsById(any())).thenReturn(false);
        mockMvc.perform(get(url)
                        .param("number", "A001AA125")
                        .param("VIN", "1234567")
                        .param("manufacturer","TOYOTA")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
}
