package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/")
  public String test(Authentication authentication) {

    // TODO: hacer que nuestro nombre de usuario se despliege.

    return "¡Bienvenid@!, tu nombre es: " + authentication.getName();
  }

}