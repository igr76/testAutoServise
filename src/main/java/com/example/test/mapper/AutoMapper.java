package com.example.test.mapper;


import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;
import com.example.test.entity.Auto;
import com.example.test.entity.Driver;
import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * маппер для {@link com.example.test.entity.Auto}
 * готовый DTO {@link com.example.test.dto.AutoDto}
 */
@Mapper(componentModel = "spring")
public interface AutoMapper {
    Auto toEntity(AutoDto autoDto);
    AutoDto toDTO(Auto auto);
    Collection<AutoDto> toDtoList(Collection<Auto> autos);
}
