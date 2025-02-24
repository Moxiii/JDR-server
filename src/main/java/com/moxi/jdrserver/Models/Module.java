package com.moxi.jdrserver.Models;

import com.moxi.jdrserver.DTO.ModuleDTO;
import com.moxi.jdrserver.Enums.ModuleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isTemplate;
    private ModuleType type;
    private String picture;
    public Module() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Module(String title, String description, User createdBy, ModuleType type, Boolean isTemplate) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.type = type;
        this.isTemplate = isTemplate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Module(ModuleDTO moduleDTO, User createdBy) {
        this.title = moduleDTO.getTitle();
        this.description = moduleDTO.getDescription();
        this.createdBy = createdBy;
        this.type = moduleDTO.getType();
        this.isTemplate = moduleDTO.getIsTemplate();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}