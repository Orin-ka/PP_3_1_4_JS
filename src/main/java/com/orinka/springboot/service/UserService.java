package com.orinka.springboot.service;

import com.orinka.springboot.entity.Role;
import com.orinka.springboot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void createUser(User user);
   // void createUser(User user, Set<Role> roles);
    void saveUser(User user);
    void delete(User user);
    void update(User user, Set<Role> roles);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
}