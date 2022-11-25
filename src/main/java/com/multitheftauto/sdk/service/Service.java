package com.multitheftauto.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multitheftauto.sdk.MTA;
import com.multitheftauto.sdk.element.Player;
import com.multitheftauto.sdk.element.Team;
import com.multitheftauto.sdk.exception.*;
import com.multitheftauto.sdk.lua.Argument;
import com.multitheftauto.sdk.model.Authentication;
import com.multitheftauto.sdk.model.Color;
import com.multitheftauto.sdk.model.Resource;
import com.multitheftauto.sdk.model.Server;
import com.multitheftauto.sdk.util.Base64;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.X509Certificate;
import java.util.List;

public class Service {
    private final MTA mta;

    private final Server server;

    private final Authentication authentication;

    public Service(MTA mta, Server server, Authentication authentication){
        this.mta = mta;
        this.server = server;
        this.authentication = authentication;
    }

    public MTA getMTA() {
        return mta;
    }

    public Server getServer() {
        return server;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public Object callFunction(String function, Argument argument)
            throws
            Exception {
        if (argument == null){
            argument = new Argument(function);
        } else {
            argument.getList().add(0, function);
        }
        return callFunction("javasdk", "callFunction", argument, null);
    }

    public <T> T callFunction(String function, Argument argument, Class<T> valueType)
            throws
            Exception {
        if (argument == null){
            argument = new Argument(function);
        } else {
            argument.getList().add(0, function);
        }
        return callFunction("javasdk", "callFunction", argument, valueType);
    }

    public Object callFunction(String resource, String function, Argument argument) throws
            Exception {
        return callFunction(resource, function, argument, null);
    }

    public <T> T callFunction(String resource, String function, Argument argument, Class<T> valueType)
            throws
            Exception {

        HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(insecureContext())
                .build();

        String json = "[]";
        if (argument != null){
            json = argument.toJSON();
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", Base64.encode((this.authentication)))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .uri(URI.create(getEndpointToResourceFunction(resource, function)))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        switch (response.statusCode()) {
            case 200 -> {
                if (response.body().equals("error: not found")) {
                    throw new FunctionNotFoundException();
                } else if (response.body().equals("error: resource not running")) {
                    throw new ResourceException("Resource not running.");
                }
                if (!mta.isResourceExists(resource)){
                    mta.addResource(new Resource(resource, this));
                }
            }
            case 401 -> throw new AccessDeniedException();
            case 404 -> throw new NotFoundStatusException();
            default -> throw new SomethingWentWrongException(response.statusCode(), response.body());
        }

        ObjectMapper mapper = new ObjectMapper();
        if (valueType != null) {
            if (valueType.isArray()){
                if (response.body().startsWith("[[") && response.body().endsWith("]]")){
                    T obj = mapper.readValue(response.body().substring(1, response.body().length() - 1), valueType);
                    for (T t : (T[]) obj){
                        if (t instanceof Team team){
                            team.setService(this);
                        } else if (t instanceof Player player){
                            player.setService(this);
                        }
                    }
                    return obj;
                } else {
                    throw new Exception("Not array!");
                }
            } else {
                if (response.body().startsWith("[") && response.body().endsWith("]")){
                    if (valueType.isAssignableFrom(Color.class)){
                        List<Integer> list = mapper.readValue(response.body(), new TypeReference<>() {});
                        if (list.size() == 3) {
                            return (T) new Color(list.get(0), list.get(1), list.get(2));
                        } else if (list.size() == 4) {
                            return (T) new Color(list.get(0), list.get(1), list.get(2), list.get(3));
                        }
                    }
                } else {
                    throw new Exception("Array!");
                }
            }
        } else {
            List<T> list = mapper.readValue(response.body(), new TypeReference<>() {});
            return list.get(0);
        }

        return null;
    }

    private String getEndpointToResourceFunction(String resource, String function){
        return String.format("%s://%s/%s/call/%s",
                this.server.getProtocol().name().toLowerCase(),
                this.server.getEndpoint(),
                resource,
                function
        );
    }

    private static SSLContext insecureContext() {
        TrustManager[] noopTrustManager = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] xcs, String string) {}
                    public void checkServerTrusted(X509Certificate[] xcs, String string) {}
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
        try {
            SSLContext sc = SSLContext.getInstance("ssl");
            sc.init(null, noopTrustManager, null);
            return sc;
        } catch (Exception ignored) {}

        return null;
    }
}
