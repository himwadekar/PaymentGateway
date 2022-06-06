package com.payment.demo.wrapper.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Time;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestWrapper {
    @JsonProperty(value = "price")
    private double price;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "description")
    private String description;
}
