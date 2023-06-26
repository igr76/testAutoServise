package com.example.test.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Класс сущность для таблицы носков
 */
@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table( name = "driver")
public class Driver {

    /**
     * Фамилия Имя Отчество водителя
     */
    @Column(name = "fio")
    String FIO;
    /**
     * Паспорт водителя
     */
    @Id
    @Column(name = "passport")
    Integer passport;
    /**
     * Категория прав
     */
    @Column(name = "category")
    String category;
    /**
     * Дата рождения водителя
     */
    @Column(name = "born")
    String born;
    /**
     * Водительский стаж
     */
    @Column(name = "experience")
    Integer experience;
    /**
     * Счет водителя
     */
    @Column(name = "dollar")
    Double dollar;
    /**
     * Автомобили водителя
     */
    @OneToMany
    @Column(name = "auto_id")
    Set<Auto> auto;

}
