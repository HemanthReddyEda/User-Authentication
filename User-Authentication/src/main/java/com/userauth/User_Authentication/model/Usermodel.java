package com.userauth.User_Authentication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.regex.Pattern;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usermodel {
    @Id
    private int userid;

    private String name;
    private String email;
    private String password;
    private String mobilenumber;
    private String role;

    // Generate a random user ID with some additional value
    public void setuserid() {
        this.userid += generaterandom();
    }

    // Generate a random number between 0 and 999
    private int generaterandom() {
        return (int) (Math.random() * 1000);
    }

    // Returns the length of the mobile number
    public int lenofmoblienumber() {
        return (mobilenumber != null) ? String.valueOf(mobilenumber).length() : 0; // Returns length or 0 if null
    }

    // Returns the length of the password
    public int lenofpassword() {
        return (password != null) ? password.length() : 0; // Returns length or 0 if null
    }

    // Regular expression for validating email format
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Method to validate email format
    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches(); // Checks if email is not null and matches pattern
    }
}
