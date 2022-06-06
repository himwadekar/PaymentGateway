package com.payment.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "MERCHANT_BANK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantBank{

    @Id
    @Column(name = "MERCHANT_ID", updatable = false, nullable = false,  columnDefinition = "VARCHAR(64)")
    private String merchant_id;

    @Column(name = "TRANSACTION_ID", nullable = false,  columnDefinition = "VARCHAR(64)")
    private String transaction_id;

    @Column(name = "BALANCE", columnDefinition = "DOUBLE")
    private Double balance;

}
