package com.payment.demo.service.impl;

import com.payment.demo.service.MerchantBankService;
import com.payment.demo.service.PaymentGatewayService;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service("PaymentGatewayServiceImpl")
@Slf4j
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    @Autowired
    MerchantBankService merchantBank;

    @Override
    public PaymentResponseWrapper executePayment(String merchantId, String cardNumber, String cvv, Double amount) throws Exception {
//        UUID merchantId = UUID.fromString("1191f989-0390-45ab-bd54-1cf312ad1b4e");
        return merchantBank.validatePaymentMethod(cardNumber, cvv, amount, merchantId);
    }
}
