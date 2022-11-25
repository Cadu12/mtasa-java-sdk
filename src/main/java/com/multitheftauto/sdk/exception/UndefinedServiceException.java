package com.multitheftauto.sdk.exception;

public class UndefinedServiceException extends MTAException {
    public UndefinedServiceException(String name){
        super(String.format("Resource %s can not be called because MTA service is not defined.", name));
    }
}
