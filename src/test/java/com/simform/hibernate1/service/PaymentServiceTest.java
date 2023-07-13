package com.simform.hibernate1.service;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@DisplayName("Service layer : test cases")
class PaymentServiceTest {
    @Mock
    private PaymentInfoRepository paymentInfoRepository;
    private PaymentService paymentService;
    AutoCloseable autoCloseable;
    PaymentInfo paymentInfo;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        paymentService = new PaymentService(paymentInfoRepository);
        paymentInfo = new PaymentInfo("11111", 100.0, "CREDIT");
    }

    @AfterEach
    void tearDown() {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Case: Create PaymentInfo")
    void testCreatePaymentInfo() {
        mock(PaymentInfo.class);
        mock(PaymentInfoRepository.class);
        when(paymentInfoRepository.save(paymentInfo)).thenReturn(paymentInfo);
        assertThat(paymentService.createPaymentInfo(paymentInfo)).isEqualTo(paymentInfo);
    }

    @Test
    @DisplayName("Case: Update PaymentInfo")
    void testUpdatePaymentInfo() {
        mock(PaymentInfo.class);
        mock(PaymentInfoRepository.class);

        when(paymentInfoRepository.save(paymentInfo)).thenReturn(paymentInfo);
        assertThat(paymentService.updatePaymentInfo(paymentInfo)).isEqualTo("success");
    }

    @Test
    @DisplayName("Case: Delete PaymentInfo")
    void testDeleteByAccountNo() {
        mock(PaymentInfo.class);
        mock(PaymentInfoRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(paymentInfoRepository).deleteByAccountNo(any());
        assertThat(paymentService.deleteByAccountNo(paymentInfo.getAccountNo())).isEqualTo("success");
    }

    @Test
    @DisplayName("Case: Get PaymentInfo by Account No")
    void testGetPaymentInfoByAccount() {
        mock(PaymentInfo.class);
        mock(PaymentInfoRepository.class);
        when(paymentInfoRepository.findByAccountNo("11111")).thenReturn(paymentInfo);
        assertThat(paymentService.getPaymentInfoByAccount("11111").getPaymentId()).isEqualTo(paymentInfo.getPaymentId());
    }

    @Test
    @DisplayName("Case: Get All PaymentInfo")
    void testGetAllPaymentInfo() {
        mock(PaymentInfo.class);
        mock(PaymentInfoRepository.class);
        when(paymentInfoRepository.findAll()).thenReturn(
            new ArrayList<PaymentInfo>(Collections.singleton(paymentInfo))
        );
        assertThat(paymentService.getAllPaymentInfo().get(0).getAccountNo()).isEqualTo(paymentInfo.getAccountNo());
    }
}