package com.payment.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMER_BANK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBank{

    @Column(name = "CUSTOMER_ID", updatable = false, nullable = false,  columnDefinition = "VARCHAR(64)")
    private String customer_id;

    @Id
    @Column(name = "CARD_NUMBER", nullable = false, columnDefinition = "VARCHAR(16)")
    private String card_number;

    @Column(name = "CVV", nullable = false, columnDefinition = "INT")
    private int cvv;

    @Column(name = "BANALCE", columnDefinition = "DOUBLE")
    private Double balance;

}
