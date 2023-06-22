package com.example.test.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
//@Table( name = "driver")
public class Driver {
    /**
     * Составной ключ, первая часть цвет
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "passport")
    Integer passport;
    /**
     * Составной ключ, вторая часть количества хлопка
     */
    @Column(name = "fio")
    String FIO;
    /**
     * Колчиество на складе
     */
    @Column(name = "category")
    String category;
    /**
     * Колчиество на складе
     */
    @Column(name = "born")
    LocalDate born;
    /**
     * Колчиество на складе
     */
    @Column(name = "experience")
    Integer experience;
    /**
     * Колчиество на складе
     */
    @Column(name = "dollar")
    Integer dollar;
    /**
     * Колчиество на складе
     */
//    @OneToMany
//    @Column(name = "auto_id")
//    Set<Auto> auto;

}
