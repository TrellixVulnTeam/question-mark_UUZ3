package com.dev.iccaka.questionmark.services;

import com.dev.iccaka.questionmark.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private JpaRepository<User, Long> repository;

    public UserServiceImpl(JpaRepository<User, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listUsers() {
        return this.repository.findAll();
    }
}