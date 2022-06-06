package com.payment.demo.rest;

import com.payment.demo.model.ExceptionResponse;
import com.payment.demo.model.MerchantBank;
import com.payment.demo.wrapper.v1.MerchantResponseWrapper;
import com.payment.demo.wrapper.v1.PaymentResponseWrapper;
import com.paypal.base.rest.PayPalRESTException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("paymentManagement/api/v1/makePayment")
public interface PaymentGatewayRest {

    @PostMapping(path = "/pay")
    @Operation(
            description = "Process Payment",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Payment processed successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentResponseWrapper.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class),
                                    examples = @ExampleObject(value = "{\"error\" : {\"code\" : 401, \"message\" : \"Unauthorized\", \"status\" : \"\"}}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Invalid data was sent",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class),
                                    examples = @ExampleObject(value = "{\"error\" : {\"code\" : 422, \"message\" : \"Invalid data was sent\", \"status\" : \"\"}}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "502",
                            description = "Bad gateway",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class),
                                    examples = @ExampleObject(value = "{\"error\" : {\"code\" : 502, \"message\" : \"Bad gateway\", \"status\" : \"\"}}")
                            )
                    )
            }
    )
    public PaymentResponseWrapper executeTransaction(@RequestParam(name = "amount", defaultValue = "0.0") Double amount,
                                                     @RequestParam(name = "cardNumber", defaultValue = "") String cardNumber,
                                                     @RequestParam(name = "cvv", defaultValue = "") String cvv,
                                                     @RequestParam(name = "merchantId", defaultValue = "") String merchantId
    ) throws Exception;


    @GetMapping(path = "/getAccountDetails")
    @Operation(
            description = "Get Account Details",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Details Fetched successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MerchantResponseWrapper.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class),
                                    examples = @ExampleObject(value = "{\"error\" : {\"code\" : 401, \"message\" : \"Unauthorized\", \"status\" : \"\"}}")
                            )
                    )
            }
    )
    public MerchantResponseWrapper getAccountDetails(@RequestParam(name = "merchantId", defaultValue = "") String merchantId
    ) throws Exception;
}
