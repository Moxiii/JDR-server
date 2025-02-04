package com.moxi.jdrserver.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import java.util.Date;

@Getter
@Setter
@Entity
public class User {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "createdBy")
    private List<Module> modules;

    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = passwordEncoder.encode(password);
    }
}
