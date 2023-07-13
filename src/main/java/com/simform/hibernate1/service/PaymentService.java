package com.simform.hibernate1.service;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.exception.*;
import com.simform.hibernate1.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PaymentService {
    @Autowired
    PaymentInfoRepository paymentInfoRepository;

    public PaymentService(PaymentInfoRepository paymentInfoRepository) {
        this.paymentInfoRepository = paymentInfoRepository;
    }

    public PaymentInfo createPaymentInfo(PaymentInfo paymentInfo){
        paymentInfoRepository.save(paymentInfo);
        return paymentInfo;
    }
    public String updatePaymentInfo(PaymentInfo paymentInfo){
        paymentInfoRepository.save(paymentInfo);
        return "success";
    }
    public String deleteByAccountNo(String accountNo){
        paymentInfoRepository.deleteByAccountNo(accountNo);
        return "success";
    }

    public PaymentInfo getPaymentInfoByAccount(String accountNo){
        if(paymentInfoRepository.findByAccountNo(accountNo) == null){
            throw new UserNotFoundException();
        }
        return paymentInfoRepository.findByAccountNo(accountNo);
    }

    public List<PaymentInfo> getAllPaymentInfo(){
        return paymentInfoRepository.findAll();
    }
}
