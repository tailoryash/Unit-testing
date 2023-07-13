package com.simform.hibernate1.repository;

import com.simform.hibernate1.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Test cases of REST APIs : Repository Layer")
class PaymentInfoRepositoryTest {
    @Autowired
    PaymentInfoRepository paymentInfoRepository;
    PaymentInfo paymentInfo;

    @BeforeEach
    void setUp() {
        paymentInfo = PaymentInfo.builder()
                .accountNo("23456")
                .amount(23456.66)
                .cardType("DEBIT").build();
        paymentInfoRepository.save(paymentInfo);
        System.out.println(paymentInfo);
    }

    @AfterEach
    void tearDown() {
        paymentInfo = null;
        paymentInfoRepository.deleteAll();
    }
    @Test
    @DisplayName("Find By CardType test cases with Success case")
    void testFindByCardType_Found(){
        List<PaymentInfo> debitCards = paymentInfoRepository.findByCardType("DEBIT");
        assertThat(debitCards.get(0).getPaymentId()).isEqualTo(paymentInfo.getPaymentId());
        assertThat(debitCards.get(0).getAmount()).isEqualTo(paymentInfo.getAmount());
    }
    @Test
    @DisplayName("Find By CardType test cases with failure case")
    void testFindByCardType_NotFound(){
        List<PaymentInfo> creditCards = paymentInfoRepository.findByCardType("CREDIT");
        assertThat(creditCards.isEmpty()).isTrue();
    }
}