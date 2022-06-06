package com.payment.demo.model;

class ErrorInfo {
    public Integer code;
    public String message;
    public String status;
}

public class ExceptionResponse {
    public ErrorInfo error;
}
