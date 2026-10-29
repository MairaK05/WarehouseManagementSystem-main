package com.warehouse_backend.wh_backend.web.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse_backend.wh_backend.services.UserService;
import com.warehouse_backend.wh_backend.web.models.Users;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service){
        this.service = service;
    }

    @GetMapping("/getEmployeeID")
    public Long getEmployeeID(@RequestParam(name="username", required= true)String username){
        Users user = this.service.getByUsername(username);
        return user.getEmployeeID();
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return this.service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("LOGIN PLEASE");
        System.out.println(user.getUsername());
        System.out.println(user);
        return this.service.verify(user); // Returns the JWT
    }
}
