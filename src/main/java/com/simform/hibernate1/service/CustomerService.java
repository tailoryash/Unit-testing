package com.simform.hibernate1.service;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        return customersList;
    }
}
