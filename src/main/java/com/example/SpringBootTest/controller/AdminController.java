package com.example.SpringBootTest.controller;

import com.example.SpringBootTest.model.User;
import com.example.SpringBootTest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(ModelMap model){
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "/users";
    }

    @GetMapping("admin")
    public String show(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("person", userService.getUser(id));
        return "/admin";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("newUser") User user) {
        return "create";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam(value = "roleUser", required = false) String roleUser,
                          @RequestParam(value = "roleAdmin", required = false) String roleAdmin) {
        String[] roles = new String[] {roleUser, roleAdmin};
        roles = Arrays.stream(roles).filter(Objects::nonNull).toArray(String[]::new);
        userService.add(user, roles);
        return "redirect:/users";
    }

    @GetMapping("edit")
    public String edit(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("updateUser", userService.getUser(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("updateUser") User user,
                         @RequestParam(value = "roleUser", required = false) String roleUser,
                         @RequestParam(value = "roleAdmin", required = false) String roleAdmin) {
        String[] roles = new String[] {roleUser, roleAdmin};
        roles = Arrays.stream(roles).filter(Objects::nonNull).toArray(String[]::new);
        userService.update(user, roles);
        return "redirect:/users";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id")Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}