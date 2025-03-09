package com.moxi.jdrserver.Service;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.UserProfile;
import com.moxi.jdrserver.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired

    private UserProfileRepository userProfileRepository;

    public UserProfile findUserProfileByUser(User user) {
        UserProfile userProfile = userProfileRepository.findUserProfileByUser(user);
        return userProfile;
    }

    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfile findByUserId(Integer userId) {
        UserProfile userProfile = userProfileRepository.findUserProfileByUserId(userId);
        return userProfile;
    }

}
