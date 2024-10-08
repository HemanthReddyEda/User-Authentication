package com.userauth.User_Authentication.service;


import com.userauth.User_Authentication.model.Usermodel;
import com.userauth.User_Authentication.repository.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userservice {
    @Autowired
    private Userrepo userrepo;

    public Object adduser(Usermodel usermodel) {
        if(usermodel.lenofmoblienumber()<10){
            return "Invalid mobile number";
        }
        if(usermodel.lenofpassword()<8){
            return "Password must be at least 8 characters long";
        }
        if(usermodel.getRole()==null){
            usermodel.setRole("customer");
        }
        if (!usermodel.isValidEmail(usermodel.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        usermodel.setuserid();
        userrepo.save(usermodel);
        return usermodel;
    }

    public String login(String name, String password) {
        Optional<Usermodel> optionalUser = userrepo.findByName(name);
        if(optionalUser.isEmpty()){
            optionalUser = userrepo.findByEmail(name);
        }

        // Check if user is present
        if (optionalUser.isPresent()) {
            Usermodel user = optionalUser.get();

            // Check if the provided password matches the stored password
            if (user.getPassword().equals(password)) {
                return "Login successful";
            }
        }
        return "Invalid credentials";
    }
}
