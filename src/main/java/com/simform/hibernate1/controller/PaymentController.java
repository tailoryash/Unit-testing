package com.simform.hibernate1.controller;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<PaymentInfo> getPaymentInfoByAccountNo(@PathVariable("accountNo") String accNo) {
        PaymentInfo paymentInfoByAccount = paymentService.getPaymentInfoByAccount(accNo);
        if (paymentInfoByAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentInfoByAccount, HttpStatus.OK);
    }

    @GetMapping
    public List<PaymentInfo> getAllPaymentInfo() {
        List<PaymentInfo> allPaymentInfo = paymentService.getAllPaymentInfo();
        return allPaymentInfo;
    }

    @PostMapping
    public ResponseEntity<PaymentInfo> createPaymentInfoDetails(@RequestBody PaymentInfo paymentInfo) {
        if (paymentInfo == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else {
            paymentService.createPaymentInfo(paymentInfo);
            return new ResponseEntity<>(paymentInfo, HttpStatus.CREATED);
        }
    }

    @PutMapping
    public String updatePaymentInfo(@RequestBody PaymentInfo paymentInfo) {
        paymentService.updatePaymentInfo(paymentInfo);
        return "Updated successfully";
    }

    @DeleteMapping("/{accountNo}")
    public String deletePaymentDetailsByAccountNo(@PathVariable("accountNo") String accNo) {
        paymentService.deleteByAccountNo(accNo);
        return "PaymentInfo deleted successfully";
    }
}
