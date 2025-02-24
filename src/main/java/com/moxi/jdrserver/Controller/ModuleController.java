package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Config.Utils.SecurityUtils;
import com.moxi.jdrserver.DTO.ModuleDTO;
import com.moxi.jdrserver.Models.Module;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequireAuthorization
//fait en sorte d'apliquer la logique d'auth a toutes les routes et permet la sécurisation avec JWT par exemple plus implement :)
// Potentiellement on peut lui rajouter un @PreAuhorize sur une route ou un controller en particulier pour filter les roles utilisateur pouvant accéder aux routes
@Slf4j
// Sl4j c'est pour les log => utilisation log.warning("text") / log.debug("text") / log.info("text") + des variable sont injectables comme log.info("Utilisateur : " , user)
@RestController
@RequestMapping("/api/module/") // tape sur localhost/user/...
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/")
    public ResponseEntity<?> getAllModules() {
        List<ModuleDTO> modules = moduleService.findAll()
                .stream()
                .map(ModuleDTO::new).toList();

        return ResponseEntity.ok(modules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable("id") int id) {
        Optional<Module> module = moduleService.findById(id);
        if (module.isPresent()) {
            return ResponseEntity.ok(new ModuleDTO(module.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserModule() {
        User user = SecurityUtils.getCurrentUser();
        List<ModuleDTO> modules = moduleService.findByUser(user)
                .stream()
                .map(ModuleDTO::new).toList();
        return ResponseEntity.ok(modules);
    }

    @PostMapping("/")
    public ResponseEntity<?> addModule(@RequestBody ModuleDTO moduleDTO) {
        ModuleDTO module = new ModuleDTO(moduleService.create(new Module(moduleDTO, SecurityUtils.getCurrentUser())));
        return ResponseEntity.status(HttpStatus.CREATED).body(module);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateModule(@PathVariable("id") int id, @RequestBody ModuleDTO moduleDTO) {
        ModuleDTO module = new ModuleDTO(moduleService.update(id, moduleDTO));
        return ResponseEntity.ok(module);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable("id") int id) {
        moduleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
