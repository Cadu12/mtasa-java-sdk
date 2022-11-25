package com.multitheftauto.sdk.exception;

public class NotFoundStatusException extends MTAException {
    public NotFoundStatusException(){
        super("There was a problem with the request. Ensure that the resource exists and that the name is spelled correctly.");
    }
}
