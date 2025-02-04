package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequireAuthorization
@Slf4j
// Sl4j c'est pour les log => utilisation log.warning / log.debug / log.info("text")
@RestController
@RequestMapping("user/") // tape sur localhost/user/...
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ResponseEntity<String> getAllUsers() {
        log.info("This is info");
        log.warn("This is warning");
        log.debug("This is debug");
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users.toString());
    }
    @GetMapping("/marco")
    public ResponseEntity<String> getMarco() {
        User user = userService.findUserByUsername("marco");
        return ResponseEntity.ok(user.getUsername());
    }
    @GetMapping("/drizzy")
    public ResponseEntity<String> getDrizzy() {
        User user = userService.findUserByUsername("drizzy");
        return ResponseEntity.ok(user.getUsername());
    }
    @GetMapping("/tim")
    public ResponseEntity<String> getTim() {
        User user = userService.findUserByUsername("tim");
        return ResponseEntity.ok(user.getUsername());
    }
    @GetMapping("/SummyFrog")
    public ResponseEntity<String> getSummyFrog() {
        User user = userService.findUserByUsername("SummyFrog");
        return ResponseEntity.ok(user.getUsername());
    }
}
