package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String hashed_password;
    private String token;
    private String token_expiration;
    private String name;

    //Id

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    //Hashed Password

    public String getHashedPassword() {

        return hashed_password;
    }

    public void setHashedPassword(String hashedpassword) {

        this.hashed_password = hashedpassword;
    }

    //Token

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    //Token expiration

    public String getTokenExpiration() {

        return token_expiration;
    }

    public void setTokenExpiration(String token_expiration) {

        this.token_expiration = token_expiration;
    }

    //Name

    public String getUsername() {

        return name;
    }

    public void setUsername(String name) {

        this.name = name;
    }
    
}