package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
// Sl4j c'est pour les log => utilisation log.warning / log.debug / log.info("text")
@RestController
@RequestMapping("user/") // tape sur localhost/user/...
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public ResponseEntity<String> getAllUsers() {
        log.info("This is info");
        log.warn("This is warning");
        log.debug("This is debug");
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.toString());
    }
}
