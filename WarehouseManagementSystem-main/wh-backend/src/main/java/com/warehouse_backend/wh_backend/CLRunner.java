package com.warehouse_backend.wh_backend;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.warehouse_backend.wh_backend.entities.EmployeeEntity;
import com.warehouse_backend.wh_backend.entities.UserEntity;
import com.warehouse_backend.wh_backend.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;
import com.warehouse_backend.wh_backend.repositories.UserRepository;
@Component
public class CLRunner implements CommandLineRunner {
    private final UserRepository userRepository;

    public CLRunner(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        Iterable<UserEntity> users = this.userRepository.findAll();
        UserEntity user = this.userRepository.findByUsername("m4ira");
        System.out.println("Employee" + user);
    }
}
