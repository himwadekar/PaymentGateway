package com.payment.demo.service.impl;

import com.payment.demo.dao.MerchantBankDao;
import com.payment.demo.model.MerchantBank;
import com.payment.demo.service.CustomerBankService;
import com.payment.demo.service.MerchantBankService;
import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("MerchantBankServiceImpl")
@Slf4j
public class MerchantBankServiceImpl implements MerchantBankService {

    @Autowired
    CustomerBankService customerBankService;

    @Autowired
    MerchantBankDao merchantBankDao;

    @Override
    public PaymentResponseWrapper validatePaymentMethod(String cardNumber, String cvv, Double amount, String merchantId) throws Exception {

        String transaction_id = UUID.randomUUID().toString();
        if(customerBankService.validatePaymentMethod(cardNumber, amount)){
            log.info("Payment Method Validated");
            PaymentResponseWrapper response = this.requestPayment(cardNumber, cvv, amount, merchantId);
            log.info("PaymentResponseWrapper response {}", response);
            return response;
        }

        PaymentResponseWrapper response = new PaymentResponseWrapper();
        response.setTransaction_status("Failed");
        response.setTransaction_id(transaction_id);
        response.setDescription("Payment Method Failed");

        return response;
    }

    @Override
    public PaymentResponseWrapper requestPayment(String cardNumber, String cvv, Double amount, String merchantId) throws Exception {
        Boolean status = customerBankService.payment(cardNumber, cvv, amount);
        String transaction_id = UUID.randomUUID().toString();
        if(status){
            log.info("Merchant_id : {}", merchantId);
            MerchantBank merchantDetails = merchantBankDao.getMerchantDetails(merchantId);
            merchantDetails.setBalance(merchantDetails.getBalance() + amount);
            merchantDetails.setTransaction_id(transaction_id);
            log.info("merchantDetails final {}", merchantDetails);
            merchantBankDao.save(merchantDetails);

            PaymentResponseWrapper response = new PaymentResponseWrapper();
            response.setTransaction_status("Success");
            response.setTransaction_id(transaction_id);
            response.setBalance(merchantDetails.getBalance().toString());

            response.setDescription("Order Placed Successfully");
            return response;
        }
        else{
            PaymentResponseWrapper response = new PaymentResponseWrapper();
            response.setTransaction_status("Failed");
            response.setTransaction_id(transaction_id);
            response.setDescription("Payment Failed");
        }
        return null;
    }

    @Override
    public MerchantResponseWrapper getPaymentDetails(String merchantId){

        try {
            MerchantResponseWrapper merchantResponseWrapper = new MerchantResponseWrapper();
            log.info("Merchant_id : {}", merchantId);
            MerchantBank details = merchantBankDao.getMerchantDetails(merchantId);
            merchantResponseWrapper.setMerchant_Id(details.getMerchant_id());
            merchantResponseWrapper.setBalance(details.getBalance().toString());
            merchantResponseWrapper.setTransaction_Id(details.getTransaction_id());

            return merchantResponseWrapper;
        }
        catch(Exception e){
            log.info("Exception caught while fetching merchant payment details: {}", e.getMessage());
        }

        return null;
    }
}
