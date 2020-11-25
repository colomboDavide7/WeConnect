package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClientServerChannel extends Thread {

    private final Socket clientSocket;

    public ClientServerChannel(AbstractConnection conn, Socket clientSocket) throws ConnectException {
        this.clientSocket = clientSocket;
        AbstractFileManager fileManager = conn.getFileManagerAssociatedToConnection();
        fileManager.setConnectionInfoToWrite(createConnectionInfo());
        fileManager.writeToOrCreate();
    }

    private String createConnectionInfo(){
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, clientSocket.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(this.clientSocket.getLocalPort()));
        return AbstractFormatter.formatConnectionInfo(info);
    }

    @Override
    public void run(){
        // TODO - starting communicating with client
        System.out.println("Channel is open...");
    }

}
