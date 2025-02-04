package com.moxi.jdrserver.Service;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.Module;
import com.moxi.jdrserver.Repository.ModuleRepository;
import com.moxi.jdrserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }
    public List<Module> findByUser(User user) {
        return moduleRepository.findByCreatedBy(user);
    }


}
