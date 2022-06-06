package com.payment.demo.service.impl;

import com.payment.demo.dao.CustomerBankDao;
import com.payment.demo.model.CustomerBank;
import com.payment.demo.service.CustomerBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("CustomerBankServiceImpl")
@Slf4j
public class CustomerBankServiceImpl implements CustomerBankService {

    @Autowired
    CustomerBankDao customerBankDao;

    @Override
    public boolean validatePaymentMethod(String cardNumber, Double amount){
        try{
            CustomerBank customerDetails = customerBankDao.getCustomerDetails(cardNumber);
            if(customerDetails.getBalance() > amount){
                return true;
            }
        }
        catch (Exception e){
            log.error("Exception caught at CustomerBank Dao : {}", e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public Boolean payment(String cardNumber, String cvv, Double amount) {

        try{
            CustomerBank customerDetails = customerBankDao.getCustomerDetails(cardNumber);
            Double accountBalance = customerDetails.getBalance();
            if((accountBalance > amount) && ((customerDetails.getCvv() == Integer.parseInt(cvv)))){

                customerDetails.setBalance(accountBalance - amount);
                return true;
            }
        }
        catch (Exception e){
            log.error("Exception caught at CustomerBank Dao : {}", e.getMessage());
            return false;
        }

        return false;
    }
}
