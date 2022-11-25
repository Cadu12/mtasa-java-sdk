package com.multitheftauto.sdk.model;

import com.multitheftauto.sdk.exception.InvalidArgumentException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    private final String host;

    private final int port;

    private final Protocol protocol;

    public Server(String host, int port) throws InvalidArgumentException {
        this(host, port, Protocol.HTTP);
    }

    public Server(String host, int port, Protocol protocol) throws InvalidArgumentException {
        try {
            InetAddress.getAllByName(host);
        } catch (UnknownHostException e){
            throw new InvalidArgumentException();
        }

        this.host = host;
        this.port = port;
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getEndpoint() {
        return String.format("%s:%s", this.host, this.port);
    }
}
