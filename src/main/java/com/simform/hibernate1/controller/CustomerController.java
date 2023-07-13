package com.simform.hibernate1.controller;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = customerService.findCustomers();
        return ResponseEntity.ok(customers);
    }
}
