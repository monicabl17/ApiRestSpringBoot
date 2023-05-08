package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.UserModel;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private TokenService tokenService;

    @Autowired
    UserService userService;

    //Aquí autenticamos al usuario e invocamos el método para generar un token

    @PostMapping("/userlogin")
    public Optional<UserModel> login(@RequestParam("name") String name, @RequestParam("hashed_password") String hashed_password) {

        String token = tokenService.generateToken(name);
        UserModel user = new UserModel();
        user.getUsername();
        user.getHashedPassword();
        user.setToken(token);
        return userService.login(name, hashed_password);

    }

}