package com.orinka.springboot.controller;

import com.orinka.springboot.entity.User;
import com.orinka.springboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecondController {
    private final UserServiceImp userService;

    @Autowired
    public SecondController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

/*    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }*/
}
