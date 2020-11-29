package com.githubcolomboDavide7.client;

import com.githubcolomboDavide7.connection.KnownServer;

import java.net.*;

public interface IClientConnection {

    void close(KnownServer server) throws ConnectException;

    void open(KnownServer server) throws ConnectException;

    boolean alreadyConnected(KnownServer server);

}
