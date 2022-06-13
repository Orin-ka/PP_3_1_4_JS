package com.orinka.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OneController {

    @GetMapping("/admin")
    public String getAdmin() {
        return "index-admin";
    }

    @GetMapping("/user")
    public String getUser() {
        return "index-user";
    }
}
