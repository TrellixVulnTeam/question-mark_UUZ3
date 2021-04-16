package com.dev.iccaka.questionmark.services;

import com.dev.iccaka.questionmark.dtos.UserDto;
import com.dev.iccaka.questionmark.entities.User;
import com.dev.iccaka.questionmark.exceptions.UserAlreadyExistsException;
import com.dev.iccaka.questionmark.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UsersRepository repository;

    public UserService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listUsers() {
        return this.repository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return  this.repository.getByUsername(username);
    }

    @Override
    public User registerUser(UserDto userDto) throws UserAlreadyExistsException {
        if(this.emailExist(userDto.getEmail())){
            throw new UserAlreadyExistsException("There's an account with that email address: " + userDto.getEmail());
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        return this.repository.save(user);
    }

    private boolean emailExist(String email){
        return this.repository.findByEmail(email).isPresent();
    }
}
