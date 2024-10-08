package com.userauth.User_Authentication.controller;


import com.userauth.User_Authentication.model.Usermodel;
import com.userauth.User_Authentication.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Usercontroller {
    @Autowired
    private Userservice userservice;


    @PostMapping("/register")
    public ResponseEntity<?> adduser(@RequestBody Usermodel usermodel){
        return ResponseEntity.ok(userservice.adduser(usermodel));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String name, @RequestParam String password){
        return ResponseEntity.ok(userservice.login(name,password));
    }
}
