package com.orinka.springboot.controller;

import com.orinka.springboot.entity.User;
import com.orinka.springboot.service.RoleServiceImp;
import com.orinka.springboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    public AdminController(@Autowired UserServiceImp userService,
                           @Autowired RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(@CurrentSecurityContext(expression = "authentication?.name") String username, ModelMap model) {

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("allRoles", roleService.findAll());
        return "index";
    }

    @GetMapping(value = "/users/{id}")
    public String getUser(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping(value = "/users/new")
    public  String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value ="/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/users/{id}")
    public String delete (@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
