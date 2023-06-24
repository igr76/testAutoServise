package com.example.test.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

/**
 * Класс сущность автомобиля
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table( name = "auto")
public class Auto {
    /**
     * Номер автомобиля
     */
    @Id
    @Column(name = "auto_number")
    String number;
    /**
     * VIN номер автомобиля
     */
    @Column(name = "auto_vin")
    Integer VIN;
    /**
     * Производитель автомобиля
     */
    @Column(name = "manufacturer")
    String manufacturer;
    /**
     * марка автомобиля
     */
    @Column(name = "model")
    String model;
    /**
     * год производства автомобиля
     */
    @Column(name = "year_of_release")
    Integer year_of_release;
    /**
     * Детали автомобиля
     */
    @ElementCollection
    @CollectionTable(name = "auto_list_of_details", joinColumns = @JoinColumn(name = "details_id"))
    @Column(name = "list_of_details")
    List<String> details;
    /**
     * Владелец автомобиля
     */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    Driver driver;
}
