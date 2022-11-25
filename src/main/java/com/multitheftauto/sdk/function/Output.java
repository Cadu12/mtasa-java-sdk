package com.multitheftauto.sdk.function;

import com.multitheftauto.sdk.element.Element;
import com.multitheftauto.sdk.element.Player;
import com.multitheftauto.sdk.lua.Argument;
import com.multitheftauto.sdk.model.Color;
import com.multitheftauto.sdk.service.Service;

public class Output {
    public static Boolean clearChatBox(Service service, Element element){
        try {
            return (boolean)service.callFunction("clearChatBox", new Argument(element));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean outputChatBox(Service service, String text, Element element, Color color, boolean colorCoded){
        try {
            return (boolean)service.callFunction("outputChatBox", new Argument(text, element, color, colorCoded));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean outputConsole(Service service, String text, Element element){
        try {
            return (boolean)service.callFunction("outputConsole", new Argument(text, element));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean outputDebugString(Service service, String text, int level, Color color){
        try {
            return (boolean)service.callFunction("outputDebugString", new Argument(text, level, color));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean outputServerLog(Service service, String text){
        try {
            return (boolean)service.callFunction("outputServerLog", new Argument(text));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean showChat(Service service, Player player, boolean show){
        try {
            return (boolean)service.callFunction("showChat", new Argument(player, show));
        } catch (Exception ignored){}

        return null;
    }

    public static Boolean showChat(Service service, Player player, boolean show, boolean inputBlocked){
        try {
            return (boolean)service.callFunction("showChat", new Argument(player, show, inputBlocked));
        } catch (Exception ignored){}

        return null;
    }
}
