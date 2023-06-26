package com.example.test.service;

import com.example.test.dto.AutoDto;
import com.example.test.entity.Auto;
import com.example.test.exception.ElemNotFound;
import com.example.test.mapper.AutoMapper;
import com.example.test.repository.AutoRepository;
import com.example.test.service.impl.ServiceAutoImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceAutoTest {
    @Mock
    private AutoRepository autoRepository;
    @Mock
    private AutoMapper autoMapper;
    @InjectMocks
    private ServiceAutoImpl serviceAuto;
    Auto auto;
    AutoDto autoDto;
    List<String> autos = new ArrayList<>();
    List<Auto> autoList = new ArrayList<>();
    List<AutoDto> autoDtoList = new ArrayList<>();
    String number = "A000AA125";
    int ONE=1;
    @BeforeEach
    void setUp() {
        auto = new Auto("A000AA125",1234567,"TOYOTA","CERES",2007,autos,null);
        autoDto = new AutoDto("A000AA125",1234567,"TOYOTA","CERES",2007,null);
        autos.add("auto"); autoList.add(auto); autoDtoList.add(autoDto);}

    @Test
    void getAutoTest() {
        when(autoRepository.findById(any())).thenReturn(Optional.ofNullable(auto));
        when(autoMapper.toDTO(any())).thenReturn(autoDto);
        assertThat(serviceAuto.getAuto(number)).isEqualTo(autoDto);
        verify(autoRepository,times(ONE)).findById(any());
    }
//    @Test
//    void getAllAutoTest() {
//        when(autoRepository.findAll()).thenReturn(autoList);
//        when(autoMapper.toDtoList(any())).thenReturn(autoDtoList);
//        assertThat(serviceAuto.getAllAuto()).isEqualTo(autoDtoList);
//        verify(autoRepository,times(ONE)).findAll();
//    }
    @Test
    void addAutoTest() {
        when(autoRepository.save(auto)).thenReturn(auto);
        when(autoMapper.toEntity(any())).thenReturn(auto);
        when(autoRepository.existsById(any())).thenReturn(false);
        assertThat(serviceAuto.addAuto(autoDto)).isEqualTo(autoDto);
        verify(autoRepository, times(ONE)).save(any());
    }
    @Test
    void addAutoNegativeTest() {
        when(autoRepository.existsById(any())).thenReturn(true);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> serviceAuto.addAuto(autoDto));
    }
    @Test
    void updateAutoTest() {
        when(autoRepository.save(auto)).thenReturn(auto);
        when(autoMapper.toEntity(any())).thenReturn(auto);
        when(autoRepository.existsById(any())).thenReturn(true);
        assertThat(serviceAuto.updateAuto(autoDto)).isEqualTo(autoDto);
        verify(autoRepository, times(ONE)).save(any());
    }
    @Test
    void updateAutoNegativeTest() {
        when(autoRepository.existsById(any())).thenReturn(false);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> serviceAuto.updateAuto(autoDto));
    }
    @Test
    void deleteAutoTest() {
        when(autoRepository.existsById(anyString())).thenReturn(true);
        assertThat(autoRepository.findById(anyString())).isNotNull();
        assertDoesNotThrow(() -> serviceAuto.deleteAuto("A000AA125"));
    }
}
