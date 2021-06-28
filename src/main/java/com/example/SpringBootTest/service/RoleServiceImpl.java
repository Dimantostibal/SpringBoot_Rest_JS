package com.example.SpringBootTest.service;

import com.example.SpringBootTest.dao.RoleDao;
import com.example.SpringBootTest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByRole(String role) {
        return roleDao.getRoleByRole(role);
    }
}