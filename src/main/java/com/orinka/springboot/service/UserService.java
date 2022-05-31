package com.orinka.springboot.service;

import com.orinka.springboot.entity.Role;
import com.orinka.springboot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void createUser(User user);
    void saveUser(User user);
    void delete(User user);
    void update(User user);
    void update(User user, Long id);
    void update(User user, Set<Role> roles);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
}