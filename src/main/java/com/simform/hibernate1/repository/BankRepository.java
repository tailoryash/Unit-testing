package com.simform.hibernate1.repository;

import com.simform.hibernate1.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    public List<Bank> findByBankName(String bankName);
}