package com.moxi.jdrserver.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
    private String picture;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserProfile(User user, String picture, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.picture = picture;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}



