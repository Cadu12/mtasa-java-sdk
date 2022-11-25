package com.multitheftauto.sdk.element;

public enum Type {
    Player,
    Ped,
    Vehicle,
    Object,
    Pickup,
    Marker,
    Blip,
    RadarArea,
    Team,
    Water,
    CollisionShape("colshape");

    private final String name;
    Type(){
        this.name = super.name();
    }

    Type(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name.toLowerCase();
    }
}
