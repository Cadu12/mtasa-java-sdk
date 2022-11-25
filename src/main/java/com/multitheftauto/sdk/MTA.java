package com.multitheftauto.sdk;

import com.multitheftauto.sdk.element.Element;
import com.multitheftauto.sdk.exception.InvalidArgumentException;
import com.multitheftauto.sdk.model.Authentication;
import com.multitheftauto.sdk.model.Resource;
import com.multitheftauto.sdk.model.Server;
import com.multitheftauto.sdk.service.Service;

import java.util.ArrayList;
import java.util.List;

public class MTA extends Functions {
    private final List<Resource> resources = new ArrayList<>();

    public MTA(String host, int port, Authentication authentication) throws InvalidArgumentException {
        this(new Server(host, port), authentication);
    }

    public MTA(String host, int port, String username, String password) throws InvalidArgumentException {
        this(new Server(host, port), new Authentication(username, password));
    }

    public MTA(Server server, String username, String passowrd){
        this(server, new Authentication(username, passowrd));
    }

    public MTA(Server server, Authentication authentication){
        setService(new Service(this, server, authentication));
    }

    public void addResource(Resource resource){
        resources.add(resource);
    }

    public Resource getResource(String name){
        for (Resource resource : resources) {
            if (resource.getName().equals(name)){
                return resource;
            }
        }

        Resource resource = new Resource(name, this.service);
        resources.add(resource);
        return resource;
    }

    public List<Resource> getResources(){
        return this.resources;
    }

    public boolean isResourceExists(String name){
        for (Resource resource : resources) {
            if (resource.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
