package com.dev.iccaka.questionmark.services;

import com.dev.iccaka.questionmark.dtos.UserDto;
import com.dev.iccaka.questionmark.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private JpaRepository<User, Long> repository;

    public UserService(JpaRepository<User, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listUsers() {
        return this.repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());

        return this.repository.save(user);
    }
}
