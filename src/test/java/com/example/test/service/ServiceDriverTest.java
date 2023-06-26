package com.example.test.service;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.dto.USD;
import com.example.test.entity.Auto;
import com.example.test.entity.Driver;
import com.example.test.mapper.AutoMapper;
import com.example.test.mapper.DriverMapper;
import com.example.test.repository.AutoRepository;
import com.example.test.repository.DriverRepository;
import com.example.test.service.impl.ServiceAutoImpl;
import com.example.test.service.impl.ServiceDriverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ServiceDriverTest {
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private DriverMapper driverMapper;
    @InjectMocks
    private ServiceDriverImpl serviceDriver;
    Driver driver;
    DriverDto driverDto;
    List<String> autos = new ArrayList<>();
    List<Driver> driverList = new ArrayList<>();
    List<DriverDto> driverDtoList = new ArrayList<>();
    Integer passport = 1000234567;
    int ONE=1;
    int TWO=2;
    @BeforeEach
    void setUp() {
        driver = new Driver("Иванов Иван Иванович",1000234567,"AB","20-02-2023",14,1289.,null);
        driverDto = new DriverDto("Иванов Иван Иванович",1000234567,"AB","20-02-2023",14,1289., USD.R_USD);
        driverList.add(driver); driverDtoList.add(driverDto);}

    @Test
    void getDriverTest() {
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        when(driverMapper.toDTO(any())).thenReturn(driverDto);
        assertThat(serviceDriver.getDriver(passport)).isEqualTo(driverDto);
        verify(driverRepository,times(ONE)).findById(any());
    }
    @Test
    void getAllDriverTest() {
        when(driverRepository.findAll()).thenReturn(driverList);
        when(driverMapper.toDtoList(any())).thenReturn(driverDtoList);
        assertThat(serviceDriver.getAllDriver()).isEqualTo(driverDtoList);
        verify(driverRepository,times(ONE)).findAll();
    }
    @Test
    void addDriverTest() {
        when(driverRepository.save(driver)).thenReturn(driver);
        when(driverMapper.toEntity(any())).thenReturn(driver);
        when(driverRepository.existsById(any())).thenReturn(false);
        assertThat(serviceDriver.addDriver(driverDto)).isEqualTo(driverDto);
        verify(driverRepository, times(ONE)).save(any());
    }
    @Test
    void addDriverNegativeTest() {
        when(driverRepository.existsById(any())).thenReturn(true);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> serviceDriver.addDriver(driverDto));
    }
    @Test
    void updateDriverTest() {
        when(driverRepository.save(driver)).thenReturn(driver);
        when(driverMapper.toEntity(any())).thenReturn(driver);
        when(driverRepository.existsById(any())).thenReturn(true);
        assertThat(serviceDriver.updateDriver(driverDto)).isEqualTo(driverDto);
        verify(driverRepository, times(ONE)).save(any());
    }
    @Test
    void updateDriverNegativeTest() {
        when(driverRepository.existsById(any())).thenReturn(false);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> serviceDriver.updateDriver(driverDto));
    }
    @Test
    void deleteDriverTest() {
        when(driverRepository.existsById(anyInt())).thenReturn(true);
        assertDoesNotThrow(() -> serviceDriver.deleteDriver(1000234567));
    }
    @Test
    void plusDollarTest() {
        driverDto.setDollar(1389.);
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        when(driverRepository.save(driver)).thenReturn(driver);
        assertThat(serviceDriver.plusDollar(100,driverDto)).isEqualTo(driverDto);
        verify(driverRepository, times(ONE)).save(any());
    }
    @Test
    void minusDollarTest() {
        driverDto.setDollar(1189.);
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        when(driverRepository.save(driver)).thenReturn(driver);
        assertThat(serviceDriver.minusDollar(100,driverDto)).isEqualTo(driverDto);
        verify(driverRepository, times(ONE)).save(any());
    }
    @Test
    void minusDollarNegativeTest() {
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> serviceDriver.minusDollar(10000.,driverDto));

    }
}
