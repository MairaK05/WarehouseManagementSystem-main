package com.warehouse_backend.wh_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse_backend.wh_backend.entities.SalesEntity;

public interface SalesRepository extends JpaRepository<SalesEntity, Long>{
    
}
