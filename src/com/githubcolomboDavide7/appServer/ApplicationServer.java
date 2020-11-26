package com.githubcolomboDavide7.appServer;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.AbstractLogger;

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
    private final AbstractLogger logger;

    private ApplicationServer() throws ConnectException {
        this.myConn = ConnectionFactory.getServiceConnection("app_server");
        this.logger = myConn.getFileManagerAssociatedToConnection();
    }

    @Override
    public boolean matchPortNumber(int portNumber) {
        return this.myConn.matchPortNumber(portNumber);
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return myConn.matchIPAddress(ip);
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.logger.openAndReadTextFile();
    }

    @Override
    public void close() throws ConnectException {
        myConn.closeConnection();
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

}
