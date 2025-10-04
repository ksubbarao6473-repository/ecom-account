package com.ecommerce.app.account.controller;

import com.ecommerce.app.account.entity.Customer;
import com.ecommerce.app.account.exception.DatabaseException;
import com.ecommerce.app.account.model.CustomerDto;
import com.ecommerce.app.account.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    UUID uuid = UUID.randomUUID();
    String uuidString = uuid.toString();
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto result = null;
            try {
                result = customerService.saveCustomer(customerDto);
                log.info("Customer saved successfully: {}", result);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } catch (DatabaseException e) {
                log.error("Error saving customer", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = null;
        try {
            customers = customerService.getAllCustomers();
            log.info("Fetched {} customers", customers.size());
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (DatabaseException e) {
            log.error("Error fetching customers", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("email") String email) {
        Customer customer = null;
        try {
             customer = customerService.getByEmail(email);
            if (customer == null) {
                log.warn("Customer with email {} not found", email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Fetched customer: {}", customer);
            return ResponseEntity.ok(customer);
        } catch (DatabaseException e) {
            log.error("Error fetching customer with email {0}, error message {1}" + email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Customer> updateCustomer(@PathVariable Long id) {
        Customer updatedCustomer = null;
        try {
            updatedCustomer = customerService.updateCustomer(id);
            log.info("Customer updated successfully: {}", updatedCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (DatabaseException e) {
            log.error("Error updating customer", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
