package com.example.user_management_system.service;

import com.example.user_management_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void deleteUser(Long id);
}
