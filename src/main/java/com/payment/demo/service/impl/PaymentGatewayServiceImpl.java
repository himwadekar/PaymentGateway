package com.payment.demo.service.impl;

import com.payment.demo.service.MerchantBankService;
import com.payment.demo.service.PaymentGatewayService;
import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PaymentGatewayServiceImpl")
@Slf4j // This is used for Logging
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    //Autowired is used as an instance of the class
    @Autowired
    MerchantBankService merchantBank;

    @Override
    public PaymentResponseWrapper executePayment(String merchantId, String cardNumber, String cvv, Double amount) throws Exception {
        return merchantBank.validatePaymentMethod(cardNumber, cvv, amount, merchantId);
    }

    @Override
    public MerchantResponseWrapper getAccountDetails(String merchantId){
        return merchantBank.getPaymentDetails(merchantId);
    }
}
