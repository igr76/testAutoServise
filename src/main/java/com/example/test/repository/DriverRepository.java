package com.example.test.repository;

import com.example.test.entity.Auto;
import com.example.test.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий {@link Driver}
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
