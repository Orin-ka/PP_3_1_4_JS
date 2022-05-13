package com.orinka.springboot.service;

import com.orinka.springboot.entity.User;
import com.orinka.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) {
        ///проверить корректность метода

        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
