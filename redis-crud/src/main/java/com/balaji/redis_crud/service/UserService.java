package com.balaji.redis_crud.service;


import com.balaji.redis_crud.entity.User;
import com.balaji.redis_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        System.out.println("Fetching from Database...");
        return userRepository.findById(id);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @CachePut(value = "users", key = "#result.id")
    public User updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null; // Handle this case properly in your application
    }

    @CachePut(value = "users", key = "#result.id")
    public User createUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
