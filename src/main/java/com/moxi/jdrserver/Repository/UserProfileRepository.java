package com.moxi.jdrserver.Repository;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findUserProfileByUser(User user);
}