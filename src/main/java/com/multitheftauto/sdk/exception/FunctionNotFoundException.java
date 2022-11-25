package com.multitheftauto.sdk.exception;

public class FunctionNotFoundException extends MTAException {
    public FunctionNotFoundException(){
        super("Attempted function call was not found.");
    }
}
