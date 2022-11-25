package com.multitheftauto.sdk.element;

import com.multitheftauto.sdk.exception.MTAException;
import com.multitheftauto.sdk.lua.Argument;
import com.multitheftauto.sdk.service.Service;

public class Player extends Element{
    private Player(String id) throws MTAException {
        super(id);
    }

    @Override
    public Type getType(){
        return Type.Player;
    }

    public static Player getPlayerFromName(Service service, String name){
        try {
            return service.callFunction("getPlayerFromName", new Argument(name), Player.class);
        } catch (Exception ignored){}

        return null;
    }

    public static Player[] getElements(Service service) {
        try {
            return service.callFunction("getElementsByType", new Argument("player"), Player[].class);
        } catch (Exception ignored){}

        return null;
    }

    public static Player[] getPlayersInTeam(Service service, Team team){
        try {
            return service.callFunction("getPlayersInTeam", new Argument(team), Player[].class);
        } catch (Exception ignored){}

        return null;
    }

    public String getName(){
        try {
            return (String)this.getService().callFunction("getPlayerName", new Argument(this));
        } catch (Exception ignored){}

        return null;
    }

    public Boolean setName(String name){
        try {
            return (boolean)this.getService().callFunction("setPlayerName", new Argument(this, name));
        } catch (Exception ignored){}

        return null;
    }

    public Integer getMoney(){
        try {
            return (int)this.getService().callFunction("getPlayerMoney", new Argument(this));
        } catch (Exception ignored){}

        return null;
    }

    public Boolean setMoney(int amount){
        return this.setMoney(amount, false);
    }

    public Boolean setMoney(int amount, boolean instant){
        try {
            return (boolean)this.getService().callFunction("setPlayerMoney", new Argument(this, amount, instant));
        } catch (Exception ignored){}

        return null;
    }

    public Team getTeam(){
        try {
            return this.getService().callFunction("getPlayerTeam", new Argument(this), Team.class);
        } catch (Exception ignored){}

        return null;
    }

    public Boolean setTeam(Team team){
        try {
            return (boolean)this.getService().callFunction("setPlayerTeam", new Argument(this, team));
        } catch (Exception ignored){}

        return null;
    }
}
