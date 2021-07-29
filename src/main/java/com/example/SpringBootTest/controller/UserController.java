package com.example.SpringBootTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {

    @GetMapping("admin")
    public String indexAdmin(){
        return "admin";
    }

    @GetMapping("user")
    public String indexUser(){
        return "user";
    }
}