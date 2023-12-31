package com.ivaneskins.controller;

import com.ivaneskins.model.User;
import com.ivaneskins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getAllUsers(Model model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("allUsers", usersList);
        return "all-users";
    }

    @GetMapping("/addMewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/deleteInfo")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
