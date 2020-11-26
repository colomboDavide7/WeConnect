package com.githubcolomboDavide7.client;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.*;

public class Client implements IClient{

    public static IClient create(String ipAddress, int portNumber) throws ConnectException {
        return new Client(ipAddress, portNumber);
    }

    private final AbstractClientConnection myConn;
    private final AbstractLogger fileManager;

    private Client(String ipAddress, int portNumber) throws ConnectException {
        this.myConn = ConnectionFactory.getClientConnection(ipAddress, portNumber);
        this.fileManager = myConn.getFileManagerAssociatedToConnection();
    }

    @Override
    public boolean matchPortNumber(int portNumber) {
        return this.myConn.matchPortNumber(portNumber);
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return this.myConn.matchIPAddress(ip);
    }

    @Override
    public void close() throws ConnectException {
        this.myConn.closeConnection();
    }

    @Override
    public void open() throws ConnectException {
        this.myConn.openConnection();
    }

    @Override
    public String appendHostNameToPath(String path) {
        return myConn.appendHostName(path);
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.fileManager.openAndReadTextFile();
    }

}
