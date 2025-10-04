package com.tri.freelancemp.service;

import com.tri.freelancemp.entity.User;
import com.tri.freelancemp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User fetchUserById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found!"));
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
