package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;

public class ClientServerChannel extends Thread {

    private AbstractClientConnection clientConn;
    private AbstractServerConnection serverConn;

    public ClientServerChannel(
            AbstractServerConnection serverConnection, AbstractClientConnection clientConn)
    {
        this.clientConn = clientConn;
        this.serverConn = serverConnection;
        AbstractFileManager serverFileManager = serverConnection.getFileManagerAssociatedToConnection();
        serverFileManager.setConnectionInfoToWrite(clientConn.getConnectionInfo());
        serverFileManager.writeToOrCreate();
    }

    @Override
    public void run(){
        // TODO - starting communicating with client
        System.out.println("Channel is open...");
    }

}
