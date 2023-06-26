package com.example.test.service;

import com.example.test.dto.AutoDto;
import com.example.test.dto.DriverDto;

import java.util.List;
/**
 * Сервис автомобилей
 */
public interface ServiceAuto {
    /**  Возвращает автомобиль
     * @param number - номер автомобиля
     */
    public AutoDto getAuto(String number);
    /**  Возвращает все автомобили     */
    public List<AutoDto> getAllAuto();
    /**  Создает новый автомобиль     */
    public AutoDto addAuto(AutoDto autoDto);
    /**  Обновляет автомобиль     */
    public AutoDto updateAuto(AutoDto autoDto);
    /**  Удаляет автомобиль
     * @param number - номер автомобиля
     */
    public void deleteAuto(String number);
}
