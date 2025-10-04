package com.ecommerce.app.account.service;

import com.ecommerce.app.account.dao.CustomerRepository;
import com.ecommerce.app.account.entity.Customer;
import com.ecommerce.app.account.exception.DatabaseException;
import com.ecommerce.app.account.model.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl {

    private final ModelMapper modelMapper;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    private Customer DtoToCustomer(CustomerDto customerDto)
    {
        return modelMapper.map(customerDto, Customer.class);
    }

    private CustomerDto CustomerToDto(Customer customer)
    {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) throws DatabaseException {
        Customer customer = DtoToCustomer(customerDto);
        Customer result = customerRepository.save(customer);
        return CustomerToDto(result);
    }
    public List<Customer> getAllCustomers() throws DatabaseException {
        return customerRepository.findAll();
    }

    public Customer getByEmail(String email) throws DatabaseException {
        return customerRepository.findByEmail(email.replaceAll("\\n", "")).orElse(null);
    }

    public Customer updateCustomer(Long id) throws DatabaseException {


        return null;
    }

}
