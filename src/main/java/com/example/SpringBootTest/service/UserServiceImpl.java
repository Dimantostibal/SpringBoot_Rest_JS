package com.example.SpringBootTest.service;

import com.example.SpringBootTest.dao.UserDao;
import com.example.SpringBootTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(Long id) {
        return userDao.findById(id).get();
    }

    public void add(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.saveAndFlush(user);
    }

    public void delete(Long id) {
        userDao.deleteById(id);
    }
}