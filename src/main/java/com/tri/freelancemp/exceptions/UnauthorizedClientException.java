package com.tri.freelancemp.exceptions;

public class UnauthorizedClientException extends RuntimeException {
    public UnauthorizedClientException(String message) {
        super(message);
    }
}
