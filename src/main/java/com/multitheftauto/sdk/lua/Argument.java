package com.multitheftauto.sdk.lua;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multitheftauto.sdk.model.Color;
import com.multitheftauto.sdk.model.Vector3;

import java.util.LinkedList;

public class Argument {
    private final LinkedList<Object> list = new LinkedList<>();

    public Argument(){}

    public Argument(Object... args){
        for (Object object : args){
            this.add(object);
        }
    }

    public Argument add(Object object){
        if (object instanceof Vector3 vector){
            list.add(vector.getX());
            list.add(vector.getY());
            list.add(vector.getZ());
        } else if (object instanceof Color color) {
            list.add(color.getRed());
            list.add(color.getGreen());
            list.add(color.getBlue());
            if (color.getAlpha() != null) {
                list.add(color.getAlpha());
            }
        } else {
            list.add(object);
        }
        return this;
    }

    public LinkedList<Object> getList() {
        return list;
    }

    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception ignored){ignored.printStackTrace();}

        return "[]";
    }
}
