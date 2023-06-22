package com.example.test.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

/**
 * Класс сущность для таблицы носков
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//@Table( name = "auto")
public class Auto {
    /**
     * Составной ключ, первая часть цвет
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    int id;

//    @Column(name = "auto_number")
    String number;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
//    @Column(name = "auto_vin")
    Integer VIN;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
//    @Column(name = "manufacturer")
    String manufacturer;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
//    @Column(name = "model")
    String model;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
//    @Column(name = "year_of_release")
    Integer year_of_release;
    /**
     * Колчиество на складе
     */
//    @ElementCollection
//    @CollectionTable(name = "auto_list_of_details", joinColumns = @JoinColumn(name = "details_id"))
//    @Column(name = "list_of_details")
//    List<String> details;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "driver_id")
//    Driver driver;
}
