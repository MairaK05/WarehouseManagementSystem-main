package com.warehouse_backend.wh_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse_backend.wh_backend.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}