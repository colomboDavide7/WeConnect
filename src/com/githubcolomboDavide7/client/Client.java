package com.githubcolomboDavide7.client;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.*;

public class Client implements IClient {

    public static int CLIENT_ID = 0;

    private final List<AbstractClientConnection> connections;
    private final AbstractLogger logger;
    private final int id;

    public Client() {
        this.connections = new ArrayList<>();
        this.id = ++CLIENT_ID;
        this.logger = new ClientLogger("CLIENT" + this.id +".txt");
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
        String info = newConn.getFormattedConnectionInfo();
        this.logger.setConnectionInfoToWrite(info);
        this.logger.writeToOrCreate();
        this.connections.add(newConn);
        System.out.println("[CLIENT" + this.id + "] is connected...");
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
