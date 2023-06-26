package com.example.test.service.impl;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.entity.Auto;
import com.example.test.exception.ElemNotFound;
import com.example.test.loger.FormLogInfo;
import com.example.test.mapper.AutoMapper;
import com.example.test.repository.AutoRepository;
import com.example.test.service.ServiceAuto;
import com.example.test.service.ServiceDriver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Реализация {@link ServiceAuto}
 */
@NoArgsConstructor
@Slf4j
@Service
public class ServiceAutoImpl implements ServiceAuto {
    Auto auto;
    AutoMapper autoMapper;
    AutoRepository autoRepository;
    /** Получить автомобиль по номеру */
    @Override
    public AutoDto getAuto(String number) {
        log.info(FormLogInfo.getInfo());
        return autoMapper.toDTO(autoRepository.findById(number).orElseThrow(ElemNotFound::new));
    }
    /** Получить все автомобили */
    @Override
    public List<AutoDto> getAllAuto() {
        log.info(FormLogInfo.getInfo());
        return (List<AutoDto>) (autoMapper.toDtoList(autoRepository.findAll())).stream().sorted();
    }
    /** Добавить новый автомобиль */
    @Override
    public AutoDto addAuto(AutoDto autoDto) {
        log.info(FormLogInfo.getInfo());
        String number = autoDto.getNumber();
        if (autoRepository.existsById(number)) {
            log.error(FormLogInfo.getException());
            throw new RuntimeException("Данный автомобиль уже существует");
        } else autoRepository.save(autoMapper.toEntity(autoDto));
        return autoDto;
    }
    /** Обновить автомобиль */
    @Override
    public AutoDto updateAuto(AutoDto autoDto) {
        log.info(FormLogInfo.getInfo());
        String number = autoDto.getNumber();
        if (!autoRepository.existsById(number)) {
            log.error(FormLogInfo.getException());
            throw new RuntimeException("Данный автомобиль уже существует");
        } else autoRepository.save(autoMapper.toEntity(autoDto));
        return autoDto;
    }
    /** Удалить автомобиль по номеру  */
    @Override
    public void deleteAuto(String number) {
        log.info(FormLogInfo.getInfo());
        if (!autoRepository.existsById(number)) {
            log.error(FormLogInfo.getException());
            throw new RuntimeException("Данный автомобиль не существует");
        } else     autoRepository.deleteById(number);
    }
}
