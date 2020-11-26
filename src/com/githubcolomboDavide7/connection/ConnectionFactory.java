package com.githubcolomboDavide7.connection;

import java.net.*;

public class ConnectionFactory {

    public static AbstractServerConnection getServiceConnection(String target) throws ConnectException {
        KnownServer service = KnownServer.get(target);
        switch(service){
            case DHCP:
                return new DHCPConnection(service);
            case PROXY:
                throw new ConnectException("No proxy server already found");
            case FIREWALL:
                throw new ConnectException("No firewall server already found");
            case APP_SERVER:
                return new ApplicationServerConnection(service);
            default:
                throw new ConnectException("No server associated to " + target);
        }
    }

    public static AbstractClientConnection getClientConnection(String ip, int portNum) throws ConnectException{
        return new ClientConnection(ip, portNum);
    }

    public static AbstractClientConnection getClientConnection(Socket clientSocket) {
        return new ClientConnection(clientSocket);
    }

}
