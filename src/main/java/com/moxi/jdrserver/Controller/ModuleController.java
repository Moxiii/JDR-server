package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Config.Utils.SecurityUtils;
import com.moxi.jdrserver.Models.Module;
import com.moxi.jdrserver.Repository.ModuleRepository;
import com.moxi.jdrserver.Repository.UserRepository;
import com.moxi.jdrserver.Service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("module/") // tape sur localhost/user/...
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/")
    public ResponseEntity<?> getAllModules() {
        List<Module> modules = moduleService.findAll();
        return ResponseEntity.ok(modules);
    }
}
