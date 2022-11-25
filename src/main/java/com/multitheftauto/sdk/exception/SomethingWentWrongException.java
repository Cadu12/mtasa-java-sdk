package com.multitheftauto.sdk.exception;

public class SomethingWentWrongException extends MTAException {
    public SomethingWentWrongException(int code, String body){
        super(String.format("Something went wrong. HTTP Status Code: %s | Body: %s", code, body));
    }
}
