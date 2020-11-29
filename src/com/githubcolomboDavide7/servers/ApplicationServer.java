package com.githubcolomboDavide7.servers;

import com.githubcolomboDavide7.connection.*;
import java.net.*;
import java.util.*;

public class ApplicationServer implements IApplicationServer, Runnable {

    private static IApplicationServer TheServer = null;

    public static IApplicationServer getApplicationServer() throws ConnectException {
        if(TheServer == null)
            TheServer = new ApplicationServer();
        return TheServer;
    }

    private final AbstractServerConnection myConn;

    private ApplicationServer() throws ConnectException {
        this.myConn = ConnectionFactory.getServiceConnection("app_server");
    }

    @Override
    public void open() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(!this.myConn.isClosed()) {
            try {
                this.myConn.openConnection();
            } catch(ConnectException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws ConnectException {
        myConn.closeConnection();
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.myConn.getConnections();
    }

    @Override
    public boolean matchServer(KnownServer server) {
        return server.equalTo(KnownServer.APP_SERVER);
    }
}
