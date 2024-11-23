package com.moxi.jdrserver.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    private long id;
    private String username;
    private String password;

    public User() {

    }
}
