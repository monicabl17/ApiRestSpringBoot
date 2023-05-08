package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import com.example.demo.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    public abstract Optional<UserModel> findByName(@Param("name") String name);
}