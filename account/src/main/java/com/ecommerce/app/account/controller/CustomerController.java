package com.ecommerce.app.account.controller;

import com.ecommerce.app.account.entity.Customer;
import com.ecommerce.app.account.exception.DatabaseException;
import com.ecommerce.app.account.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer( @RequestBody @Valid Customer customer) {
        Customer result = null;
            try {
                result = customerService.saveCustomer(customer);
                log.info("Customer saved successfully: {}", result);
                return ResponseEntity.ok(result);
            } catch (DatabaseException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = null;
        try {
            customers = customerService.getAllCustomers();
            log.info("Fetched {} customers", customers.size());
            return ResponseEntity.ok(customers);
        } catch (DatabaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
