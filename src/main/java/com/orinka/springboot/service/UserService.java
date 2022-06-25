package com.orinka.springboot.service;

import com.orinka.springboot.entity.User;
import java.util.List;


public interface UserService {
    void createUser(User user);
    void delete(User user);
    void deleteUserById(Long id);
    void update(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
}