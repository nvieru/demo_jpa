package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserRepository userRepository;

    public void addUser(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));
    }
}
