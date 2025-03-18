package com.moxi.jdrserver.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ModuleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;

    public ModuleTag(Module module, String name) {
        this.module = module;
        this.name = name;
    }

    public ModuleTag() {
    }

}
