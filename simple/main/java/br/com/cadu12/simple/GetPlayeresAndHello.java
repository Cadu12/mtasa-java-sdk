package br.com.cadu12.simple;

import com.multitheftauto.sdk.MTA;
import com.multitheftauto.sdk.element.Player;
import com.multitheftauto.sdk.exception.InvalidArgumentException;
import com.multitheftauto.sdk.model.Authentication;
import com.multitheftauto.sdk.model.Server;

public class GetPlayeresAndHello {
    public static void main(String[] args) throws InvalidArgumentException {
        MTA mta = new MTA(
                new Server(Config.HOST, Config.PORT),
                new Authentication(Config.USERNAME, Config.PASSWORD)
        );

        Player[] players = mta.getPlayers();
        for (Player player : players){
            mta.outputChatBox("Hello, " + player.getName());
        }
    }
}
