package com.ecommerce.app.account.service;

import com.ecommerce.app.account.dao.CustomerRepository;
import com.ecommerce.app.account.entity.Customer;
import com.ecommerce.app.account.exception.DatabaseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) throws DatabaseException {
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() throws DatabaseException {
        return customerRepository.findAll();
    }

}
