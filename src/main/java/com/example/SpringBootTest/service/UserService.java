package com.example.SpringBootTest.service;

import com.example.SpringBootTest.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUser(Long id);

    public void add(User user);

    public void update(User user);

    public void delete(Long id);

    public User getByName(String name);
}
