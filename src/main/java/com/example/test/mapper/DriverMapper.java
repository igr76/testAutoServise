package com.example.test.mapper;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.entity.Auto;
import com.example.test.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * маппер для {@link Driver}
 * готовый DTO {@link DriverDto}
 */
@Mapper(componentModel = "spring")
public interface DriverMapper {
    @Mapping(target = "dollar", ignore = true)
    @Mapping(target = "currency", ignore = true)
    Driver toEntity(DriverDto driverDto);
    @Mapping(target = "dollar", ignore = true)
    DriverDto toDTO(Driver driver);
    Collection<DriverDto>  toDtoList(Collection<Driver> drivers);
}
