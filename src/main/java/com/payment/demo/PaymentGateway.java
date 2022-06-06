package com.payment.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties
@Configuration
@ComponentScan
@OpenAPIDefinition(
		info = @Info(title = "Payment service", version = "0.0.1", description = "Payment Gateway Module")
)
public class PaymentGateway {
	public static void main(String[] args) {
		SpringApplication.run(PaymentGateway.class, args);
	}

}
