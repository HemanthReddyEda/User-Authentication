package com.userauth.User_Authentication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.regex.Pattern;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;
    private String email;
    private String password;
    private Long mobilenumber;
    private String role;

    public void setuserid(){
        this.userid=userid+generaterandom();
    }
    public int generaterandom(){
        return (int)(Math.random()*1000);
    }

    public int lenofmoblienumber() {
        return (int) (Math.log10(mobilenumber) + 1);
    }

    public int lenofpassword() {
        return password.length();
    }
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Method to validate email format
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}

