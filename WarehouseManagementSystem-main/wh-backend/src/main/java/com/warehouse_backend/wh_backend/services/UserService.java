package com.warehouse_backend.wh_backend.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.security.authentication.AuthenticationManager;

import com.warehouse_backend.wh_backend.entities.EmployeeEntity;
import com.warehouse_backend.wh_backend.entities.UserEntity;
import com.warehouse_backend.wh_backend.repositories.EmployeeRepository;
import com.warehouse_backend.wh_backend.repositories.UserRepository;
import com.warehouse_backend.wh_backend.web.errors.BadRequestException;
import com.warehouse_backend.wh_backend.web.errors.UserAlreadyExistsException;
import com.warehouse_backend.wh_backend.web.models.Users;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authManager;

    private JWTService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authManager, JWTService jwtService, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.employeeRepository = employeeRepository;
    }

    public UserEntity translateWebToDb(Users user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmployeeID(user.getEmployeeID());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public Users translateDbToWeb(UserEntity userEntity) {
        return new Users(userEntity.getUserID(), userEntity.getUsername(), userEntity.getPassword(),
            userEntity.getEmployeeID(), userEntity.getEmail());
    }

    public Users getByUsername(String filterUsername) {
        Users user = new Users();
        if (StringUtils.hasLength(filterUsername)) {
            UserEntity entity = this.userRepository.findByUsername(filterUsername);
            user = this.translateDbToWeb(entity);
        } else {
            return null;
        }
        return user;
    }

    public Users register(Users user) {
        String username = user.getUsername();
        // check if employee repository has record of the same id AND email as the user passed in
        EmployeeEntity employee = employeeRepository.findById(user.getEmployeeID()).get();
        if (!employee.getEmail().equals(user.getEmail())){
            System.out.println(employee.getEmail() + " : " + user.getEmail());
            throw new BadRequestException("No such user with that combination of details");
        }
        if (userRepository.findByUsername(username) != null){
            throw new UserAlreadyExistsException("Username taken");
        } 
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = translateWebToDb(user);
        try {
            userRepository.save(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.translateDbToWeb(entity);
    }

    public String verify(Users user) {
        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

}