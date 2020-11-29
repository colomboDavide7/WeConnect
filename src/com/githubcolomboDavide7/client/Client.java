package com.githubcolomboDavide7.client;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.*;

public class Client implements IClient {

    private final List<AbstractClientConnection> connections;
    private final AbstractLogger logger;

    public Client() {
        this.connections = new ArrayList<>();
        this.logger = new ClientLogger("TEST.txt");         // TODO - change filename
    }

    @Override
    public void close(KnownServer server) throws ConnectException {
        if(!alreadyConnected(server))
            throw new ConnectException("Not connected to " + server.IPAddress);
        for(AbstractClientConnection c : connections)
            if(c.matchServer(server))
                shutDownAndClearConnection(c);
    }

    private void shutDownAndClearConnection(AbstractClientConnection c) throws ConnectException{
        c.closeConnection();
        this.connections.remove(c);
    }

    @Override
    public void open(KnownServer server) throws ConnectException {
        if(alreadyConnected(server))
            throw new ConnectException("Already connected to " + server.IPAddress);
        AbstractClientConnection newConn = ConnectionFactory.getClientConnection(server);
        newConn.openConnection();
        this.connections.add(newConn);
    }

    @Override
    public boolean alreadyConnected(KnownServer server) {
        for(AbstractClientConnection c : connections)
            if(c.matchServer(server))
                return true;
        return false;
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.logger.openAndReadTextFile();
    }

}
