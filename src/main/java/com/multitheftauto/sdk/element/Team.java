package com.multitheftauto.sdk.element;

import com.multitheftauto.sdk.exception.MTAException;
import com.multitheftauto.sdk.lua.Argument;
import com.multitheftauto.sdk.model.Color;
import com.multitheftauto.sdk.service.Service;

public class Team extends Element{
    private Team(String id) throws MTAException {
        super(id);
    }

    @Override
    public Type getType(){
        return Type.Team;
    }

    // static
    public static Team createTeam(Service service, String name, Color color){
        try {
            return service.callFunction("createTeam", new Argument(name, color), Team.class);
        } catch (Exception ignored){}

        return null;
    }

    public static Team getTeamFromName(Service service, String name){
        try {
            return service.callFunction("getPlayerFromName", new Argument(name), Team.class);
        } catch (Exception ignored){}

        return null;
    }

    public static Team[] getElements(Service service) {
        try {
            return service.callFunction("getElementsByType", new Argument("team"), Team[].class);
        } catch (Exception ignored){}

        return null;
    }

    // methods
    public Integer countPlayers(){
        try {
            return (int)this.getService().callFunction("countPlayersInTeam", new Argument(this));
        } catch (Exception ignored){}

        return null;
    }

    public String getName(){
        try {
            return (String)this.getService().callFunction("getTeamName", new Argument(this));
        } catch (Exception ignored){}

        return null;
    }

    public boolean setName(String name){
        try {
            this.getService().callFunction("setTeamName", new Argument(this, name));
            return true;
        } catch (Exception ignored){}

        return false;
    }

    public Color getColor(){
        try {
            return this.getService().callFunction("getTeamColor", new Argument(this), Color.class);
        } catch (Exception ignored){}

        return null;
    }

    public boolean setColor(int red, int green, int blue){
        return setColor(new Color(red, green, blue));
    }

    public boolean setColor(Color color){
        try {
            this.getService().callFunction("setTeamColor", new Argument(this, color));
            return true;
        } catch (Exception ignored){}

        return false;
    }

    public Boolean isFriendlyFire(){
        try {
            return (boolean)this.getService().callFunction("getTeamFriendlyFire", new Argument(this));
        } catch (Exception ignored){}

        return null;
    }

    public boolean setFriendlyFire(boolean friendlyFire){
        try {
            this.getService().callFunction("setTeamFriendlyFire", new Argument(this, friendlyFire));
            return true;
        } catch (Exception ignored){}

        return false;
    }
}
