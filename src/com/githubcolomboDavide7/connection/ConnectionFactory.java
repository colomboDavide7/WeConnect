package com.githubcolomboDavide7.connection;

import java.net.*;

public class ConnectionFactory {

    public static AbstractServerConnection getDHCPConnection() throws ConnectException {
        return new DHCPConnection();
    }

    public static AbstractServerConnection getApplicationServerConnection() throws ConnectException{
        return new ApplicationServerConnection();
    }

}
