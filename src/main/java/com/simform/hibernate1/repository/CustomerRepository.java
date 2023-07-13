package com.simform.hibernate1.repository;

import com.simform.hibernate1.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}