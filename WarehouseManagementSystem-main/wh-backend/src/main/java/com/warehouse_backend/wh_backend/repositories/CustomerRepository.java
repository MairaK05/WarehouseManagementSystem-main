package com.warehouse_backend.wh_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse_backend.wh_backend.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
}
