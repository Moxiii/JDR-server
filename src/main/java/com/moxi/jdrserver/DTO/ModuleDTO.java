package com.moxi.jdrserver.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ModuleDTO {
    private String title;
    private String description;
    private String author;
    private String authorId;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isTemplate;
}
