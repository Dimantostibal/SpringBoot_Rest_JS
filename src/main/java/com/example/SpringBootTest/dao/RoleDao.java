package com.example.SpringBootTest.dao;

import com.example.SpringBootTest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role getRoleByRole(String role);
}