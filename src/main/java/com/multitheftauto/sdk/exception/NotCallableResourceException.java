package com.multitheftauto.sdk.exception;

public class NotCallableResourceException extends MTAException {
    public NotCallableResourceException(){
        super("There was a problem with the request. Ensure that the resource handling the call is running.");
    }
}
