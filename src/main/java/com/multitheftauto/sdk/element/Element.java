package com.multitheftauto.sdk.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multitheftauto.sdk.element.serializer.ElementSerializer;
import com.multitheftauto.sdk.exception.MTAException;
import com.multitheftauto.sdk.service.Service;

import java.util.Objects;

@JsonSerialize(using = ElementSerializer.class)
public class Element {
    public static final String SERVER_PREFIX = "^E^";

    private final int id;

    private Service service;

    public Element(String id) throws MTAException {
        if (id.startsWith("^E^")){
            this.id = Integer.parseInt(id.substring(3));
        } else {
            throw new MTAException("Not Element");
        }
    }

    public Element(int id){
        this.id = id;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public Type getType() {
        return null;
    }

    @JsonIgnore
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
