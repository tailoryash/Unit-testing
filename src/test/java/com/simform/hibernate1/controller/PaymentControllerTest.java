package com.simform.hibernate1.controller;

import com.fasterxml.jackson.databind.*;
import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@DisplayName("Controller layer : test cases")
class PaymentControllerTest {
    PaymentInfo paymentInfo1;
    PaymentInfo paymentInfo2;
    List<PaymentInfo> paymentInfoList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentInfo1 = new PaymentInfo("10001", 10000.0, "DEBIT");
        paymentInfo2 = new PaymentInfo("10002", 2000.0, "CREDIT");
        paymentInfoList.add(paymentInfo1);
        paymentInfoList.add(paymentInfo2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("success case : create")
    void testCreatePaymentInfoDetails_Found() throws Exception {
        //object to json
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(paymentInfo1);

        when(paymentService.createPaymentInfo(paymentInfo1)).thenReturn(paymentInfo1);
        this.mockMvc.perform(post("/payment").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("failure case : create")
    void testCreatePaymentInfoDetails_NotFound() throws Exception {
        //object to json
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(null);
        this.mockMvc.perform(post("/payment").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("success case : get by account no")
    void testGetPaymentInfoByAccountNo_Found() throws Exception {
        when(paymentService.getPaymentInfoByAccount("10001")).thenReturn(paymentInfo1);

        this.mockMvc.perform(get("/payment/10001")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("failure case : get by account no")
    void testGetPaymentInfoByAccountNo_NotFound() throws Exception {
        when(paymentService.getPaymentInfoByAccount("10003")).thenReturn(null);
        this.mockMvc.perform(get("/payment/10003")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("case : get all")
    void testGetAllPaymentInfo() throws Exception {
        when(paymentService.getAllPaymentInfo()).thenReturn(paymentInfoList);
        this.mockMvc.perform(get("/payment")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("case : update")
    void testUpdatePaymentInfo() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(paymentInfo2);

        when(paymentService.updatePaymentInfo(paymentInfo1)).thenReturn("Updated successfully");
        this.mockMvc.perform(put("/payment").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("case : delete by acccount no")
    void testDeletePaymentDetailsByAccountNo() throws Exception {
        when(paymentService.deleteByAccountNo("10001")).thenReturn("success");
        this.mockMvc.perform(delete("/payment/10001")).andDo(print()).andExpect(status().isOk());
    }
}