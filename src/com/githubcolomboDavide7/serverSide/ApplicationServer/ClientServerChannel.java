package com.githubcolomboDavide7.serverSide.ApplicationServer;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClientServerChannel extends Thread {

    private final Socket clientSocket;
    private IApplicationServer appServer;

    public ClientServerChannel(Socket clientSocket) throws ConnectException {
        this.clientSocket = clientSocket;
        this.appServer = ApplicationServer.getApplicationServer();
        this.appServer.log(createConnectionInfo());
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
    }

}
