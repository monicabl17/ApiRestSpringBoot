package com.example.demo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.CustomerModel;
import com.example.demo.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    // GET (Obtener todos los clientes)

    public Iterable<CustomerModel> getCustomers() {

        return customerRepository.findAll();
    }

    // GET (Obtener cliente por id)

    public Optional<CustomerModel> getCustomer(Long id) {

        return customerRepository.findById(id);
    }

    // POST (Crear clientes)

    public CustomerModel createCustomer(CustomerModel customer) {

        return customerRepository.save(customer);
    }

    // PUT (Modificar cliente)

    public Optional<CustomerModel> updateCustomer(Long id, CustomerModel customer) {

        Optional<CustomerModel> _customer = customerRepository.findById(id);

        return customerRepository.save(_customer);
    }

    // DELETE (Borrar clientes)

    public void deleteCustomer(Long id) {

        this.customerRepository.deleteById(id);
    }
}