package com.orinka.springboot.service;

import com.orinka.springboot.entity.Role;
import com.orinka.springboot.entity.User;
import com.orinka.springboot.repository.RoleRepository;
import com.orinka.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
//@Slf4j
//@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        User startUser1 = new User("Olga", "Mironova", "seller", "user", "user" );
        User startUser2 = new User("Mark", "Tarkovsky", "realtor", "admin", "admin" );
        Role role1 = roleRepository.saveAndFlush(new Role("ROLE_USER"));
        Role role2 = roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));

        startUser1.setPassword(passwordEncoder.encode(startUser1.getPassword()));
        startUser2.setPassword(passwordEncoder.encode(startUser2.getPassword()));

        startUser1.setRoles(Collections.singleton(role1));
        startUser2.setRoles(Collections.singleton(role2));

        userService.saveUser(startUser1);
        userService.saveUser(startUser2);

/*        Arrays.stream(EnumRole.values())
                .map(x -> x.toString())
                .forEach(x -> roleService.addRole(new Role(x)));


        User startUser = new User("admin", "admin", 3,"admin", "admin");
        startUser.addRoleToUser(roleService.findByName("ROLE_ADMIN"));
        userService.addUser(startUser);*/
    }

    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Set<Role> roles = new HashSet<>();
        Role role = new Role("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //log.info("Saving new User with name: {}", user.getFirstName());
        userRepository.save(user);

    }


    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) {
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) { userRepository.delete(user);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

    }

    // переопределить
   // void update(User user, Set<Role> roles);
}







