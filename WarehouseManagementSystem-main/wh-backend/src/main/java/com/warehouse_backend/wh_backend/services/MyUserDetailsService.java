package com.warehouse_backend.wh_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.warehouse_backend.wh_backend.entities.UserEntity;
import com.warehouse_backend.wh_backend.repositories.UserRepository;
import com.warehouse_backend.wh_backend.web.models.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(user);
    }
}
