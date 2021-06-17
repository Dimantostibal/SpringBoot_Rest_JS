package com.example.SpringBootTest.dao;

import com.example.SpringBootTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User getUserByName(String name);
}
