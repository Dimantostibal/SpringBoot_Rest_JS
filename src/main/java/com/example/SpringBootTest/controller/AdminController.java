package com.example.SpringBootTest.controller;

import com.example.SpringBootTest.model.User;
import com.example.SpringBootTest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(ModelMap modelMap, Principal principal){
        List<User> list = userService.getAllUsers();
        modelMap.addAttribute("allUsers", list);
        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        modelMap.addAttribute("newUser", new User());
        return "admin";
    }

//    @GetMapping("admin")
//    public String show(@RequestParam("id") Long id, ModelMap model) {
//        model.addAttribute("person", userService.getUser(id));
//        return "/admin";
//    }

//    @GetMapping("/create")
//    public String createUser(@ModelAttribute("newUser") User user) {
//        return "create";
//    }

    @PostMapping("create")
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam(value = "roleUser", required = false) String[] roles){
        roles = Arrays.stream(roles).filter(Objects::nonNull).toArray(String[]::new);
        userService.add(user, roles);
        return "redirect:/admin";
    }

//    @GetMapping("edit")
//    public String edit(@RequestParam("id") Long id, ModelMap model){
//        model.addAttribute("updateUser", userService.getUser(id));
//        return "update";
//    }

    @PostMapping("update")
    public String update(@ModelAttribute("newUser") User user,
                         @RequestParam(value = "roleUser", required = false) String[] roles) {
        roles = Arrays.stream(roles).filter(Objects::nonNull).toArray(String[]::new);
        userService.update(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id")Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}