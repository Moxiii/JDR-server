package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Utils.JwtUtils;
import com.moxi.jdrserver.DTO.LoginRes;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = userService.findUserByUsername(user.getUsername());
        if(existingUser !=null){
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
                try{
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(existingUser.getUsername() , user.getPassword());
                    Authentication authentication = authenticationManager.authenticate(token);
                    if(authentication != null && authentication.isAuthenticated()){
                        SecurityContext context = SecurityContextHolder.getContext();
                        context.setAuthentication(authentication);
                        String jwtToken = jwtUtils.generateToken(existingUser);
                        LoginRes loginRes = new LoginRes();
                        loginRes.setUsername(existingUser.getUsername());
                        loginRes.setToken(jwtToken);
                        return ResponseEntity.ok(loginRes);
                    }
                } catch (AuthenticationException e) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user){
        try{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPass);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest req) {
        String token = jwtUtils.extractTokenFromRequest(req);
        if (token != null) {
            String username = jwtUtils.extractUsername(token);
            if (username != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
