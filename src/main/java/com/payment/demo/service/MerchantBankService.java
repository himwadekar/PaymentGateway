package com.payment.demo.service;

import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;

public interface MerchantBankService {
    public PaymentResponseWrapper validatePaymentMethod(String cardNumber, String cvv, Double amount, String merchantId) throws Exception;
    public PaymentResponseWrapper requestPayment(String cardNumber, String cvv, Double amount, String merchantId) throws Exception;
    public MerchantResponseWrapper getPaymentDetails(String merchantId);
}
