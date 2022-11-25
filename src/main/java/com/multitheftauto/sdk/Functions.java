package com.multitheftauto.sdk;

import com.multitheftauto.sdk.element.Element;
import com.multitheftauto.sdk.element.Player;
import com.multitheftauto.sdk.element.Team;
import com.multitheftauto.sdk.function.Output;
import com.multitheftauto.sdk.model.Color;
import com.multitheftauto.sdk.service.Service;

public class Functions {
    protected Service service;

    protected Element rootElement = null;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    // root element
    public Element getRootElement(){
        if (rootElement == null){
            rootElement = new Element(1);
        }

        return rootElement;
    }

    // Output
    public Boolean clearChatBox(){
        return clearChatBox(null);
    }

    public Boolean clearChatBox(Player player){
        if (player != null){
            return Output.clearChatBox(getService(), player);
        } else {
            return Output.clearChatBox(getService(), getRootElement());
        }
    }

    public Boolean outputChatBox(String text){
        return outputChatBox(text, new Color(231, 217, 176));
    }

    public Boolean outputChatBox(String text, Color color){
        return outputChatBox(text, null, color, false);
    }

    public Boolean outputChatBox(String text, Player player){
        return outputChatBox(text, player, new Color(231, 217, 176));
    }

    public Boolean outputChatBox(String text, Player player, Color color){
        return outputChatBox(text, player, color, false);
    }

    public Boolean outputChatBox(String text, Player player, Color color, boolean colorCoded){
        if (player != null){
            return Output.outputChatBox(getService(), text, player, color, colorCoded);
        } else {
            return Output.outputChatBox(getService(), text, getRootElement(), color, colorCoded);
        }
    }

    public Boolean outputConsole(String text){
        return outputConsole(text, null);
    }

    public Boolean outputConsole(String text, Player player){
        if (player != null){
            return Output.outputConsole(getService(), text, player);
        } else {
            return Output.outputConsole(getService(), text, getRootElement());
        }
    }

    public Boolean outputDebugString(String text){
        return outputDebugString(text, 3, new Color(255, 255, 255));
    }

    public Boolean outputDebugString(String text, int level, Color color){
        return Output.outputDebugString(getService(), text, level, color);
    }

    public Boolean outputServerLog(String text){
        return Output.outputServerLog(getService(), text);
    }

    public Boolean showChat(Player player, boolean show){
        return Output.showChat(getService(), player, show);
    }

    public Boolean showChat(Player player, boolean show, boolean inputBlocked){
        return Output.showChat(getService(), player, show, inputBlocked);
    }

    // Team
    public Team createTeam(String name){
        return createTeam(name, new Color(255, 255, 255));
    }

    public Team createTeam(String name, int red, int green, int blue){
        return createTeam(name, new Color(red, green, blue));
    }

    public Team createTeam(String name, Color color){
        return Team.createTeam(getService(), name, color);
    }

    public Team[] getTeams(){
        return Team.getElements(getService());
    }

    // Player
    public Player[] getPlayers(){
        return Player.getElements(getService());
    }

    public Player[] getPlayersInTeam(Team team){
        return Player.getPlayersInTeam(getService(), team);
    }
}
