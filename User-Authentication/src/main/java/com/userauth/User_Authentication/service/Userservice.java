package com.userauth.User_Authentication.service;


import com.userauth.User_Authentication.model.Usermodel;
import com.userauth.User_Authentication.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class Userservice {
    @Autowired
    private Userrepo userrepo;

    public Object adduser(Usermodel usermodel) {

        Map<String, String> response = new HashMap<>();
        if(usermodel.lenofmoblienumber()!=10){
            System.out.println(usermodel.lenofmoblienumber());
            response.put("message","Mobile number must be 10 digits long");
        }
        if(usermodel.lenofpassword()<8){
            response.put("message", "Password must be at least 8 characters long");
        }
        if(usermodel.getRole()==null){
            usermodel.setRole("customer");
        }
        if (!usermodel.isValidEmail(usermodel.getEmail())) {
            response.put("message", "Invalid email format");
        }
        if (!response.isEmpty()) {
            return response; // Return the errors if any
        }
        usermodel.setuserid();
        userrepo.save(usermodel);
        response.put("message", "User registered successfully");
        return response;
    }

    public Map<String, String> login(String name, String password) {
        Optional<Usermodel> optionalUser = userrepo.findByName(name);
        if(optionalUser.isEmpty()){
            optionalUser = userrepo.findByEmail(name);
        }
        Map<String, String> response = new HashMap<>();
        // Check if user is present
        if (optionalUser.isPresent()) {
            Usermodel user = optionalUser.get();

            // Check if the provided password matches the stored password
            if (user.getPassword().equals(password)) {
                response.put("message", "Login successful");
                return response;
            }
            response.put("message","Invalid password" );
        }
        response.put("message", "login details not found");
        return response;
    }

    public Object getuser() {
        return userrepo.findAll();
    }
}
