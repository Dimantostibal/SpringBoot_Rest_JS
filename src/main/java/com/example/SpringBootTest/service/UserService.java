package com.example.SpringBootTest.service;

import com.example.SpringBootTest.model.Role;
import com.example.SpringBootTest.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    void add(User user, String[] roles);

    void update(User user, String[] roles);

    void delete(Long id);

    Role getRoleById(Long id);
}
