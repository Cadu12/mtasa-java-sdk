package com.multitheftauto.sdk.exception;

public class ElementNotFoundException extends MTAException {
    public ElementNotFoundException(String name){
        super("Element not found.");
    }
}
