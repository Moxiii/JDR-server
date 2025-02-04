package com.moxi.jdrserver.Config.Manager;

import com.moxi.jdrserver.Config.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenManager {
    @Autowired
    private JwtUtils jwtUtils;
    private final Map<String , String> tokenMap = new ConcurrentHashMap<>();


    public void addToken(String username , String token){
        tokenMap.put(username , token);
    }

    public String getValidToken(String username){
        String storedToken = tokenMap.get(username);
        if(storedToken != null && jwtUtils.validateToken(storedToken)){
            return storedToken;
        }
        return null;
    }

    public void removeToken(String username){
        tokenMap.remove(username);
    }
}
