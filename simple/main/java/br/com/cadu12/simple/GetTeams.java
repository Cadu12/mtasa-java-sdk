package br.com.cadu12.simple;

import com.multitheftauto.sdk.MTA;
import com.multitheftauto.sdk.element.Team;
import com.multitheftauto.sdk.exception.InvalidArgumentException;
import com.multitheftauto.sdk.model.Authentication;
import com.multitheftauto.sdk.model.Server;

public class GetTeams {
    public static void main(String[] args) throws InvalidArgumentException {
        MTA mta = new MTA(
                new Server(Config.HOST, Config.PORT),
                new Authentication(Config.USERNAME, Config.PASSWORD)
        );

        Team[] teams = mta.getTeams();
        for (Team team : teams){
            System.out.println(team.getName());
        }
    }
}
