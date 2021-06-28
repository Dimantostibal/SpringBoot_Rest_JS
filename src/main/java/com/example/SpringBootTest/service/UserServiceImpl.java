package com.example.SpringBootTest.service;

import com.example.SpringBootTest.dao.RoleDao;
import com.example.SpringBootTest.dao.UserDao;
import com.example.SpringBootTest.model.Role;
import com.example.SpringBootTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public void add(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleDao.getRoleByRole(role));
        }
        user.setUserRoles(roleSet);
        userDao.save(user);
    }

    @Override
    public void update(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleDao.getRoleByRole(role));
        }
        user.setUserRoles(roleSet);
        userDao.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> optionalRole = roleDao.findById(id);
        return optionalRole.get();
    }
}