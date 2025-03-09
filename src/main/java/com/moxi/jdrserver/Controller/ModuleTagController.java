package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Models.ModuleTag;
import com.moxi.jdrserver.Service.ModuleTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequireAuthorization
@Slf4j
@RestController
@RequestMapping("api/moduleTag")
public class ModuleTagController {

    @Autowired
    private ModuleTagService moduleTagService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleTag(@PathVariable("id") Long id) {

        List<ModuleTag> moduleTag = moduleTagService.findModuleTagbyModuleId(id);
        return (ResponseEntity<?>) moduleTag;
    }
}
