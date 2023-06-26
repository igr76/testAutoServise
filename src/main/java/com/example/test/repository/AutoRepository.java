package com.example.test.repository;

import com.example.test.entity.Auto;
import com.example.test.service.ServiceAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий {@link Auto}
 */
@Repository
public interface AutoRepository extends JpaRepository<Auto,String> {
}
