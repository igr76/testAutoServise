package com.example.test.service;

import com.example.test.dto.DriverDto;

import java.util.List;
/**
 * Сервис водителей
 */
public interface ServiceDriver {
    /**  Возвращает водителя
     * @param passport - номер паспорта
     */
    public DriverDto getDriver(int passport);
    /**  Возвращает всех водителей     */
    public List<DriverDto> getAllDriver();
    /**  Создает нового водителя     */
    public DriverDto addDriver(DriverDto driverDto);
    /**  Обновляет водителя     */
    public DriverDto updateDriver(DriverDto driverDto);
    /**  Возвращает водителя
     * @param passport - номер паспорта
     */
    public void deleteDriver(int passport);
    /**  Пополнение валюты     */
    public DriverDto plusDollar(double money,DriverDto driverDto);
    /**  Изъятие валюты     */
    public DriverDto minusDollar(double money,DriverDto driverDto);
}
