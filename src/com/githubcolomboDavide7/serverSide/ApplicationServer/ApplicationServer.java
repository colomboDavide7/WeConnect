package com.githubcolomboDavide7.serverSide.ApplicationServer;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;

import java.net.*;
import java.util.*;

public class ApplicationServer implements IApplicationServer {

    private static IApplicationServer TheServer = null;

    public static IApplicationServer getApplicationServer() throws ConnectException {
        if(TheServer == null)
            TheServer = new ApplicationServer();
        return TheServer;
    }

    private final AbstractServerConnection myConn;
    private final AbstractFileManager serverFileManager;

    private ApplicationServer() throws ConnectException {
        this.myConn = ConnectionFactory.getApplicationServerConnection();
        this.serverFileManager = myConn.getFormatterAssociatedToConnection();
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
        return this.serverFileManager.openAndReadTextFile();
    }

    @Override
    public void log(String toLog) {
        this.serverFileManager.setConnectionInfoToWrite(toLog);
        this.serverFileManager.writeToOrCreate();
    }

    @Override
    public void close() throws ConnectException {
        myConn.closeConnection();
    }

    @Override
    public void open() throws ConnectException {
        myConn.openConnection();
    }

}
