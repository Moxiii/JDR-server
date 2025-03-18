package com.moxi.jdrserver.Controller;

import com.moxi.jdrserver.Config.Annotation.RequireAuthorization;
import com.moxi.jdrserver.Models.UserProfile;
import com.moxi.jdrserver.Service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequireAuthorization
@Slf4j
@RestController
@RequestMapping("/api/user-profil")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable("id") int id) {
        UserProfile userProfile = userProfileService.findByUserId(id);
        return ResponseEntity.ok(userProfile);
    }

}
