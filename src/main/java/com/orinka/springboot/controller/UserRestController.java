/*
package com.orinka.springboot.controller;

import com.orinka.springboot.entity.User;
import com.orinka.springboot.service.UserService;
import com.orinka.springboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(@Autowired UserService userService) {
        this.userService = userService;
    }

    public User user(Authentication auth) {
        return (User) auth.getPrincipal();
    }




    @GetMapping("")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
*/
