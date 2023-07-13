package com.simform.hibernate1.repository;

import com.simform.hibernate1.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
    public List<PaymentInfo> findByCardType(String cardType);
    public PaymentInfo findByAccountNo(String accountNo);
    public void deleteByAccountNo(String accountNo);
}