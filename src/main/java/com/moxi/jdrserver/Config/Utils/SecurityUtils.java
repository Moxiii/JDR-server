package com.moxi.jdrserver.Config.Utils;

import com.moxi.jdrserver.Models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof User){
            return (User) authentication.getPrincipal();

        }
        return null;
    }

    public static boolean isAuthenticated(HttpServletRequest request , JwtUtils jwtUtils){
        String Authorization = request.getHeader("Authorization");
        if(Authorization != null && Authorization.startsWith("Bearer ")){
            String token = jwtUtils.extractTokenFromRequest(request);
            if(jwtUtils.validateToken(token)){
                return true;}
        }
        return false;
    }
}
