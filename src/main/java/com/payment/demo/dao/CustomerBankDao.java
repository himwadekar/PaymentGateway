package com.payment.demo.dao;

import com.payment.demo.model.CustomerBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerBankDao extends JpaRepository<CustomerBank, UUID> {
    CustomerBank getCustomerDetails(String cardNumber) throws Exception;
}
