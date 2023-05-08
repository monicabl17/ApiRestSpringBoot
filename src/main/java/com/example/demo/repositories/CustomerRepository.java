package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.CustomerModel;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Long> {

    public abstract CustomerModel findByName(@Param("name") String name);

    public abstract CustomerModel findById(CustomerModel customer);

    public abstract Optional<CustomerModel> save(Optional<CustomerModel> _customer);
}