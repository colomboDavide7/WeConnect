package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClientServerChannel extends Thread {

    private final AbstractClientConnection clientConn;
    private final AbstractServerConnection serverConn;

    public ClientServerChannel(
            AbstractServerConnection serverConnection, AbstractClientConnection clientConn)
    {
        this.clientConn = clientConn;
        this.serverConn = serverConnection;
        AbstractFileManager fileManager = serverConnection.getFileManagerAssociatedToConnection();
        fileManager.setConnectionInfoToWrite(clientConn.getConnectionInfo());
        fileManager.writeToOrCreate();
    }

    public ClientServerChannel(
            AbstractClientConnection clientConn, AbstractServerConnection serverConnection)
    {
        this.clientConn = clientConn;
        this.serverConn = serverConnection;
        AbstractFileManager fileManager = clientConn.getFileManagerAssociatedToConnection();
        fileManager.setConnectionInfoToWrite(serverConnection.getConnectionInfo());
        fileManager.writeToOrCreate();
    }

    @Override
    public void run(){
        // TODO - starting communicating with client
        System.out.println("Channel is open...");
    }

}
