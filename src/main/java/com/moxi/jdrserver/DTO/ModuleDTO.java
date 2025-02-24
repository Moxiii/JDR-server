package com.moxi.jdrserver.DTO;

import com.moxi.jdrserver.Enums.ModuleType;
import com.moxi.jdrserver.Models.Module;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModuleDTO {
    private long id;
    private String title;
    private String description;
    private String author;
    private long authorId;
    private ModuleType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isTemplate;

    public ModuleDTO() {}

    public ModuleDTO(Module module) {
        this.id = module.getId();
        this.title = module.getTitle();
        this.description = module.getDescription();
        this.author = module.getCreatedBy().getUsername();
        this.authorId = module.getCreatedBy().getId();
        this.type = module.getType();
        this.createdAt = module.getCreatedAt();
        this.updatedAt = module.getUpdatedAt();
        this.isTemplate = module.getIsTemplate();
    }
}
