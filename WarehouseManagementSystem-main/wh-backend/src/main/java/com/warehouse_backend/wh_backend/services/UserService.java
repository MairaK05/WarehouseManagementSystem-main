package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;

import com.warehouse_backend.wh_backend.entities.UserEntity;
import com.warehouse_backend.wh_backend.repositories.UserRepository;
import com.warehouse_backend.wh_backend.web.models.Users;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authManager;

    private JWTService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authManager, JWTService jwtService){
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public UserEntity translateWebToDb(Users user){
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        return entity;
    }

    public Users translateDbToWeb(UserEntity userEntity){
        return new Users(userEntity.getUserID(), userEntity.getUsername(), userEntity.getPassword());

    }
    public List<Users> getByUsername(String filterUsername){
        List<Users> users = new ArrayList<>();
        if (StringUtils.hasLength(filterUsername)) {
            UserEntity entity = this.userRepository.findByUsername(filterUsername);
            users.add(this.translateDbToWeb(entity));
        } else {
            Iterable<UserEntity> entities = this.userRepository.findAll();
            entities.forEach(entity-> {
                users.add(this.translateDbToWeb(entity));
            });
        }
        return users;        
    }
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = translateWebToDb(user);
        userRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public String verify(Users user) {
        try {
        Authentication authentication = 
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

}