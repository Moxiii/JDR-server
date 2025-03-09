package com.moxi.jdrserver.Service;

import com.moxi.jdrserver.Models.ModuleTag;
import com.moxi.jdrserver.Repository.ModuleTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleTagService {

    @Autowired
    private ModuleTagRepository moduleTagRepository;

    public List<ModuleTag> findModuleTagbyModuleId(Long moduleId) {
        return moduleTagRepository.findModuleTagbyModuleId(moduleId);
    }

}
