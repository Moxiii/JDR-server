package com.moxi.jdrserver.Service;

import com.moxi.jdrserver.DTO.ModuleDTO;
import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.Module;
import com.moxi.jdrserver.Repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public Optional<Module> findById(int id) {
        return moduleRepository.findById(id);
    }
    public List<Module> findByUser(User user) {
        return moduleRepository.findByCreatedBy(user);
    }

    public Module create(Module module) {
        return moduleRepository.save(module);
    }

    public Module update(int id, ModuleDTO moduleDTO) {
        Module module = this.findById(id).orElseThrow();
        module.setTitle(moduleDTO.getTitle());
        module.setDescription(moduleDTO.getDescription());
        module.setType(moduleDTO.getType());
        module.setUpdatedAt(LocalDateTime.now());
        return moduleRepository.save(module);
    }

    public void delete(int id) {
        moduleRepository.deleteById(id);
    }
}
