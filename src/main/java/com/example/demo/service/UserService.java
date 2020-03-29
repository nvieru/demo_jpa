package com.example.demo.service;

import com.example.demo.User;
import com.example.demo.UserRepository;
import com.example.demo.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public void addUser(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));
    }
}
