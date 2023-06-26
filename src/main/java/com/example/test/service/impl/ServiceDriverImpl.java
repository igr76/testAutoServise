package com.example.test.service.impl;
import com.example.test.dto.DriverDto;
import com.example.test.dto.USD;
import com.example.test.entity.Driver;
import com.example.test.exception.ElemNotFound;
import com.example.test.loger.FormLogInfo;
import com.example.test.mapper.DriverMapper;
import com.example.test.repository.DriverRepository;
import com.example.test.service.ServiceDriver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Реализация {@link ServiceDriver}
 */
@NoArgsConstructor
@Slf4j
@Service
public class ServiceDriverImpl implements ServiceDriver {
    Driver driver;
    DriverMapper driverMapper;
    DriverRepository driverRepository;

    public ServiceDriverImpl(Driver driver, DriverMapper driverMapper, DriverRepository driverRepository) {
        this.driver = driver;
        this.driverMapper = driverMapper;
        this.driverRepository = driverRepository;
    }

    /** Получить водителя по пасспорту */
    @Override
    public DriverDto getDriver(int passport) {
        log.info(FormLogInfo.getInfo());
        return driverMapper.toDTO(driverRepository.findById(passport).orElseThrow(ElemNotFound::new));
    }
    /** Получить всех водителей */
    @Override
    public List<DriverDto> getAllDriver() {
        log.info(FormLogInfo.getInfo());
        return (List<DriverDto>) (driverMapper.toDtoList(driverRepository.findAll())).stream().sorted();
    }
    /** Добавить нового водителя */
    @Override
    public DriverDto addDriver(DriverDto driverDto) {
        log.info(FormLogInfo.getInfo());
        int passport = driverDto.getPassport();
        if (driverRepository.existsById(passport)) {
            log.error(FormLogInfo.getException());
         throw new RuntimeException("Данный водитель уже существует");
        } else
            driverRepository.save(driverMapper.toEntity(driverDto));
        return driverDto;
    }
    /** Обновить водителя */
    @Override
    public DriverDto updateDriver(DriverDto driverDto) {
         log.info(FormLogInfo.getInfo());
        int passport = driverDto.getPassport();
        if (!driverRepository.existsById(passport)) {
            log.error(FormLogInfo.getException());
            throw new RuntimeException("Данного водителя не существует");
        } else driverRepository.save(driverMapper.toEntity(driverDto));
        return driverDto;
    }
    /** Удалить водителя по номеру паспорта */
    @Override
    public void deleteDriver(int passport) {
        log.info(FormLogInfo.getInfo());
        if (!driverRepository.existsById(passport)) {
            log.error(FormLogInfo.getException());
            throw new RuntimeException("Данного водителя не существует");
        } else   driverRepository.deleteById(passport);
    }
    /** зачислить валюту */
    @Override
    public DriverDto plusDollar(double money,DriverDto driverDto) {
        log.info(FormLogInfo.getInfo());
        int passport = driverDto.getPassport();
        Driver driver1 = driverRepository.findById(passport).orElseThrow(ElemNotFound::new);
        USD usd = driverDto.getCurrency();
        switch (usd) {
            case B_USD -> {
                double result = money / 0.9 + driver1.getDollar();
                driver1.setDollar(result);
                driverRepository.save(driver1);
                driverDto.setDollar(result * 0.9);
            }
            case G_USD -> {
                double result = money / 2.5 + driver1.getDollar();
                driver1.setDollar(result);
                driverRepository.save(driver1);
                driverDto.setDollar(result * 2.5);
            }
            case R_USD -> {
                double result = money + driver1.getDollar();
                driver1.setDollar(result);
                driverRepository.save(driver1);
                driverDto.setDollar(result);
            }
        }

        return driverDto;
    }
    /** изъять валюту */
    @Override
    public DriverDto minusDollar(double money,DriverDto driverDto) {
        log.info(FormLogInfo.getInfo());
        int passport = driverDto.getPassport();
        Driver driver1 = driverRepository.findById(passport).orElseThrow(ElemNotFound::new);
        USD usd = driverDto.getCurrency();
        switch (usd) {
            case B_USD -> {double result = driver1.getDollar() - money/0.9;
                if (result > 0) {driver1.setDollar(result); driverRepository.save(driver1 );    } else throw new ArithmeticException("Недостаточно средств");
                driverDto.setDollar(result*0.9);}
            case G_USD -> {double result = driver1.getDollar() - money/2.5;
                if (result > 0) {driver1.setDollar(result); driverRepository.save(driver1 );    } else throw new ArithmeticException("Недостаточно средств");
                driverDto.setDollar(result*2.5);}
            case R_USD -> {double result = driver1.getDollar() - money;
                if (result > 0) {driver1.setDollar(result); driverRepository.save(driver1 );    } else throw new ArithmeticException("Недостаточно средств");
                driverDto.setDollar(result);}

        }

        return driverDto;
    }


}
