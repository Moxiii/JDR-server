package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Config.Utils.SecurityUtils;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequireAuthorization
//fait en sorte d'apliquer la logique d'auth a toutes les routes et permet la sécurisation avec JWT par exemple plus implement :)
// Potentiellement on peut lui rajouter un @PreAuhorize sur une route ou un controller en particulier pour filter les roles utilisateur pouvant accéder aux routes
@Slf4j
// Sl4j c'est pour les log => utilisation log.warning("text") / log.debug("text") / log.info("text") + des variable sont injectables comme log.info("Utilisateur : " , user)
@RestController
@RequestMapping("user/") // tape sur localhost/user/...
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        log.info("This is info");
        log.warn("This is warning");
        log.debug("This is debug");
        List<Map<String, Object>> users = userRepository.findAll().stream()
                .map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user.getId());
                    map.put("username", user.getUsername());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/marco")
    public ResponseEntity<String> getMarco() {
        User user = userRepository.findUserByUsername("marco");
        return ResponseEntity.ok("c'est marco le beaugoss");
    }
    @GetMapping("/drizzy")
    public ResponseEntity<String> getDrizzy() {
        User user = userRepository.findUserByUsername("drizzy");
        return ResponseEntity.ok(user.getUsername());
    }
    @GetMapping("/tim")
    public ResponseEntity<String> getTim() {
        User user = userRepository.findUserByUsername("tim");
        return ResponseEntity.ok(user.getUsername());
    }
    @GetMapping("/SummyFrog")
    public ResponseEntity<String> getSummyFrog() {
        User user = userRepository.findUserByUsername("SummyFrog");
        return ResponseEntity.ok(user.getUsername());
    }
@GetMapping("/me")
public ResponseEntity<String> getMe() {
        User currentUser = SecurityUtils.getCurrentUser();
        return ResponseEntity.ok(currentUser.getUsername());
}
}
