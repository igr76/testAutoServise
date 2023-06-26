package com.example.test.dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;
/**
 * ProductDTO for the {@link com.example.test.entity.Auto} entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AutoDto {
    /**
     * Номер автомобиля
     */
    String number;
    /**
     * VIN номер автомобиля
     */
    Integer VIN;
    /**
     * Производитель автомобиля
     */
    String manufacturer;
    /**
     * марка автомобиля
     */
    String model;
    /**
     * год производства автомобиля
     */
    Integer year_of_release;
    /**
     * Детали автомобиля
     */
    List<String> details;
}
