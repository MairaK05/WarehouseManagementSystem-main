package com.warehouse_backend.wh_backend;

import org.springframework.boot.CommandLineRunner;

import com.warehouse_backend.wh_backend.entities.UserEntity;
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
        UserEntity user = this.userRepository.findByUsername("m4ira");
        System.out.println("Employee" + user);
    }
}
