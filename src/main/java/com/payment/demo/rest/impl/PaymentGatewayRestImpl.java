package com.payment.demo.rest.impl;

import com.payment.demo.rest.PaymentGatewayRest;
import com.payment.demo.service.PaymentGatewayService;
import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Order;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("onboardingManagement/api/v1/makePayment")
public class PaymentGatewayRestImpl implements PaymentGatewayRest {

    @Autowired
    PaymentGatewayService paymentGatewayService;

    @Override
    @GetMapping(path = "/pay")
    public PaymentResponseWrapper executeTransaction(Double amount, String cardNumber, String cvv, String merchantId){
        PaymentResponseWrapper paymentResponseWrapper = new PaymentResponseWrapper();
        UUID paymentId = UUID.randomUUID();
        LocalDateTime date = LocalDateTime.now();
        try {
            PaymentResponseWrapper paymentDetails = paymentGatewayService.executePayment(merchantId, cardNumber, cvv, amount);
            log.info("Payment Details : {}", paymentDetails);
            return paymentDetails;

        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" Payment response {}", paymentResponseWrapper);
        paymentResponseWrapper.setTransaction_id(UUID.randomUUID().toString());
        paymentResponseWrapper.setTransaction_status("Error");
        paymentResponseWrapper.setDescription("Server Encountered an Error.");
        return paymentResponseWrapper;
    }

    @Override
    @GetMapping(path = "/getAccountDetails")
    public MerchantResponseWrapper getAccountDetails(String merchantId){
        return paymentGatewayService.getAccountDetails(merchantId);
    }
}