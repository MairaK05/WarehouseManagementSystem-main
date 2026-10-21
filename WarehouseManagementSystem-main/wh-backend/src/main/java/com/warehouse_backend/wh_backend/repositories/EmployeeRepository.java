package com.warehouse_backend.wh_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.warehouse_backend.wh_backend.entities.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    EmployeeEntity findByEmail(String email);
}
