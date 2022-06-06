package com.payment.demo.wrapper.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantResponseWrapper {
    private String merchant_Id;
    private String balance;
    private String transaction_Id;
}
