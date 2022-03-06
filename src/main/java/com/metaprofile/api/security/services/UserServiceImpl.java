package com.metaprofile.api.security.services;

import com.metaprofile.api.security.exceptions.SecurityUserNotFound;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UsersService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(SecurityUserNotFound::new);
    }
}
