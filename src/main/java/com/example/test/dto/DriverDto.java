package com.example.test.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
/**
 * ProductDTO for the {@link com.example.test.entity.Driver} entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    /**
     * Паспорт водителя
     */
    Integer passport;
    /**
     * Фамилия Имя Отчество водителя
     */
    String FIO;
    /**
     * Категория прав
     */
    String category;
    /**
     * Дата рождения водителя
     */
    LocalDate born;
    /**
     * Водительский стаж
     */
    Integer experience;
    /**
     * Счет водителя в красном долларе
     */
    Double dollar;
    /**
     * Вид валюты
     */
    USD currency;
}
