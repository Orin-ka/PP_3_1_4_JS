package com.orinka.springboot.util;

import com.orinka.springboot.entity.Role;
import com.orinka.springboot.entity.User;
import com.orinka.springboot.repository.RoleRepository;
import com.orinka.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    @Transactional
    void initial() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        User startUser1 = new User("Olga", "Mironova2", "seller", "admin", "admin");
        User startUser2 = new User("Mark", "Tarkovsky2", "realtor", "user", "user");
        Role role1 = roleRepository.saveAndFlush(new Role("ROLE_USER"));
        Role role2 = roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));

        startUser1.setPassword(passwordEncoder.encode(startUser1.getPassword()));
        startUser2.setPassword(passwordEncoder.encode(startUser2.getPassword()));

        startUser1.addRole(role1);
        startUser1.addRole(role2);
        startUser2.addRole(role1);

        userRepository.save(startUser1);
        userRepository.save(startUser2);
    }
}
