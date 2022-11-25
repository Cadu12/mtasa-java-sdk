package com.multitheftauto.sdk.exception;

public class AccessDeniedException extends MTAException {
    public AccessDeniedException(){
        super("Access Denied. This server requires authentication. Please ensure that a valid username and password combination is provided.");
    }
}
