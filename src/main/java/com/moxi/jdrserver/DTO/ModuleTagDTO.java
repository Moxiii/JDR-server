package com.moxi.jdrserver.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleTagDTO {

    private Long id;
    private String name;
    private Module module;

    public ModuleTagDTO() {
    }
    
    public ModuleTagDTO(Module module, String name) {
        this.module = module;
        this.name = name;
    }

}
