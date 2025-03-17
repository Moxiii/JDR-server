package com.moxi.jdrserver.DTO;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private User user;
    private String picture;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserProfileDTO() {
    }

    public UserProfileDTO(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.user = userProfile.getUser();
        this.picture = userProfile.getPicture();
        this.description = userProfile.getDescription();
        this.createdAt = userProfile.getCreatedAt();
        this.updatedAt = userProfile.getUpdatedAt();
    }

}
