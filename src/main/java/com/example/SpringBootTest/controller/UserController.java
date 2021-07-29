package com.example.SpringBootTest.controller;

import com.example.SpringBootTest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


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