package com.payment.demo.dao;

import com.payment.demo.model.MerchantBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantBankDao extends JpaRepository<MerchantBank, UUID> {
    MerchantBank getMerchantDetails(String merchantId) throws Exception;}
