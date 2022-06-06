package com.payment.demo.service;

import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;

public interface PaymentGatewayService {
//    public Payment createPayment(UUID paymentId, Double price, String currency, String description, LocalDateTime date, String cancelUrl, String successUrl) throws PayPalRESTException;
    public PaymentResponseWrapper executePayment(String payerId, String cardNumber, String cvv, Double amount) throws Exception;
    public MerchantResponseWrapper getAccountDetails(String merchantId);
}
