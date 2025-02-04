package com.moxi.jdrserver.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class LoginRes {
    private String username;
    private String token;

    public LoginRes(String username, String validToken) {
        this.username = username;
        this.token = validToken;
    }
}
