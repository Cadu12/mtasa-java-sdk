package com.multitheftauto.sdk.model;

import com.multitheftauto.sdk.exception.*;
import com.multitheftauto.sdk.lua.Argument;
import com.multitheftauto.sdk.service.Service;

import java.io.IOException;

public class Resource {
    private final String name;

    private final Service service;

    public Resource(String name, Service service){
        this.name = name;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public Service getService() {
        return service;
    }

    public Object call(String function, Argument argument)
            throws Exception {

        if (service == null){
            throw new UndefinedServiceException(this.name);
        }

        return service.callFunction(this.name, function, argument);
    }
}
