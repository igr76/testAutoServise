package com.example.test.controller;

import com.example.test.dto.DriverDto;
import com.example.test.dto.USD;
import com.example.test.entity.Driver;
import com.example.test.mapper.DriverMapper;
import com.example.test.repository.DriverRepository;
import com.example.test.service.impl.ServiceDriverImpl;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
public class DriverControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private DriverController driverController;
    @SpyBean
    private ServiceDriverImpl serviceDriver;
    @MockBean
    private DriverRepository driverRepository;
    @MockBean
    private DriverMapper driverMapper;
    private JSONObject driverDTO;
    private Driver driver;
    private DriverDto driverDto;
    private List<Driver> driverList = new ArrayList<>();
    double money;
    int passport;
    @BeforeEach
    void init() {
        money = 100.;
        passport = 1000234567;
        List<DriverDto> driverDtoList = new ArrayList<>();
        driver = new Driver("Иванов Иван Иванович",1000234567,"AB","20-02-2023",14,1289.,null);
        driverDto = new DriverDto("Иванов Иван Иванович",1000234567,"AB","20-02-2023",14,1289., USD.R_USD);
        driverList.add(driver); driverDtoList.add(driverDto);

    }
    @Test
    void getDriver() throws  Exception {
        String url = "/driver/" + passport;
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getAllDriver() throws  Exception {
        String url = "/driver";
        when(driverRepository.findAll()).thenReturn(driverList);
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void addDriver() throws  Exception{
                String url = "/driver" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("passport",1000234567);
        driverDTO.put("FIO","Иванов");
        driverDTO.put("category","АВС");

        when(driverRepository.existsById(any())).thenReturn(false);
        mockMvc.perform(get(url)
                        .param("passport", "1000234567")
                        .param("FIO", "Иванов")
                        .param("category","АВС")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
    @Test
    void updateDriver() throws  Exception{
        String url = "/driver" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("passport",100023456);
        driverDTO.put("FIO","Иванов");
        driverDTO.put("category","АВС");

        when(driverRepository.existsById(any())).thenReturn(false);
        mockMvc.perform(get(url)
                        .param("passport", "100023456")
                        .param("FIO", "Иванов")
                        .param("category","АВС")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
    @Test
    void plusDollar() throws  Exception{
        String url = "/" + money + "/dollar+" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("passport",100023456);
        driverDTO.put("FIO","Иванов");
        driverDTO.put("category","АВС");
        driverDTO.put("dollar",1100);


        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        mockMvc.perform(get(url)
                        .param("passport", "100023456")
                        .param("FIO", "Иванов")
                        .param("category","АВС")
                        .param("dollar","1200")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
    @Test
    void minusDollar() throws  Exception{
        String url = "/" + money + "/dollar-" ;
        JSONObject driverDTO = new JSONObject();
        driverDTO.put("passport",100023456);
        driverDTO.put("FIO","Иванов");
        driverDTO.put("category","АВС");
        driverDTO.put("dollar",1100);


        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        mockMvc.perform(get(url)
                        .param("passport", "100023456")
                        .param("FIO", "Иванов")
                        .param("category","АВС")
                        .param("dollar","1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(driverDTO))
                .andExpect(status().isOk());
    }
}
