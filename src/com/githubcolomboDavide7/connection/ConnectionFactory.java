package com.githubcolomboDavide7.connection;

import java.net.*;
import java.util.*;

public class ConnectionFactory {

    private static final List<AbstractServerConnection> serverConnectionList = new ArrayList<>();

    static{
        try {
            serverConnectionList.add(new DHCPConnection(KnownServer.DHCP));
            serverConnectionList.add(new ApplicationServerConnection(KnownServer.APP_SERVER));
        } catch(ConnectException e) {
            System.err.println("Error opening Server connections...");
            System.out.println("Quitting JVM!");
            System.exit(-1);
        }
    }

    public static AbstractServerConnection getServiceConnection(String target) throws ConnectException {
        for(AbstractServerConnection c : serverConnectionList)
            if(c.matchServer(KnownServer.get(target)))
                return c;
        throw new ConnectException("No " + target + " server found!");
    }

    public static AbstractClientConnection getClientConnection(KnownServer server) throws ConnectException{
        return new ClientConnection(server);
    }

    /*public static AbstractClientConnection getClientConnection(Socket clientSocket) {
        return new ClientConnection(clientSocket);
    }*/

}
