package com.simform.hibernate1.service;

import com.simform.hibernate1.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;
}
