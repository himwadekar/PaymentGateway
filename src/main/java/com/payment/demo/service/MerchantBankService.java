package com.payment.demo.service;

import com.payment.demo.wrapper.v1.PaymentResponseWrapper;

import java.util.Date;
import java.util.UUID;

public interface MerchantBankService {
    public PaymentResponseWrapper validatePaymentMethod(String cardNumber, String cvv, Double amount, String merchantId) throws Exception;
    public PaymentResponseWrapper requestPayment(String cardNumber, String cvv, Double amount, String merchantId) throws Exception;
}
