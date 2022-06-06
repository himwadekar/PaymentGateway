package com.payment.demo.service;

public interface CustomerBankService {
    public boolean validatePaymentMethod(String cardNumber, Double amount);
    public Boolean payment(String cardNumber, String cvv, Double amount);
}
