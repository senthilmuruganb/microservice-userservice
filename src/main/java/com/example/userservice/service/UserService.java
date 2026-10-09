package com.example.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(updatedUser.getUserName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setAge(updatedUser.getAge());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
