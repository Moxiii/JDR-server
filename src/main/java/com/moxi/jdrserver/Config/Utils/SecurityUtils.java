package com.moxi.jdrserver.Config.Utils;

import com.moxi.jdrserver.Models.CustomUserDetails;
import com.moxi.jdrserver.Models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if(userDetails instanceof CustomUserDetails){
                CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
                return customUserDetails.getUser();
            }


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
