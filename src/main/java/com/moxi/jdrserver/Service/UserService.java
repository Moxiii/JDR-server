package com.moxi.jdrserver.Service;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
