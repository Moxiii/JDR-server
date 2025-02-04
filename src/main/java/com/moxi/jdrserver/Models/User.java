package com.moxi.jdrserver.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Setter
@Entity
public class User {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "createdBy")
    private List<Module> modules;

    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = passwordEncoder.encode(password);
    }
}
