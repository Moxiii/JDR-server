package com.moxi.jdrserver.Config.Filter;

import com.moxi.jdrserver.Config.Utils.JwtUtils;
import com.moxi.jdrserver.Models.CustomUserDetails;
import com.moxi.jdrserver.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Filter(name="JwtFilter")
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

public JwtAuthFilter(JwtUtils jwtUtils)  {
    this.jwtUtils = jwtUtils;
}

@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtils.extractTokenFromRequest(request);
        if (token != null && jwtUtils.validateToken(token)) {
            String username = jwtUtils.extractUsername(token);
            UserDetails userDetails = new CustomUserDetails(userRepository.findUserByUsername(username));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //TODO delete after build API with CustomWrapper
        if (token == null || !jwtUtils.validateToken(token)) {
            String bypassToken = jwtUtils.createBypassToken();
            request= new CustomWrapper(request , bypassToken);
        }
        filterChain.doFilter(request, response);
    }
}
