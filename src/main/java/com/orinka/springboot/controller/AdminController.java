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

    @GetMapping("")
    public String allUsers(@CurrentSecurityContext(expression = "authentication?.name") String username, ModelMap model) {

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("currentUser", userService.getUserByUsername(username));
        model.addAttribute("allRoles", roleService.findAll());
        return "index";
    }

    @GetMapping(value = "/users/{id}")
    public String getUser(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

/*    @GetMapping(value = "/edit/{id}")
    public String getUser(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "index";
    }*/

    @GetMapping(value = "/users/new")
    public  String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

/*--------------------------------------*/

    @GetMapping(value = "/edit/{id}")
    public String updateUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
       // model.addAttribute(userService.getUserById(id));
        return "index";
    }

/*    @GetMapping(value ="/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "redirect:/admin";     //"edit";
    }*/

    @PatchMapping(value = "/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.update(user, id);
        return "redirect:/admin";
    }

/*------------------------------------------*/
    @DeleteMapping(value = "/users/{id}")
    public String delete (@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
