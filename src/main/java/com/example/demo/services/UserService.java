package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<UserModel> login(String name, String hashed_password) {

        return userRepository.findByName(name);

    }
}