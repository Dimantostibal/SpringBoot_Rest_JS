package com.example.SpringBootTest.service;

import com.example.SpringBootTest.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    void add(User user);

    void update(User user);

    void delete(Long id);

    UserDetails loadUserByUsername(String name);
}
