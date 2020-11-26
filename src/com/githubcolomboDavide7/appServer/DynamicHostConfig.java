package com.githubcolomboDavide7.appServer;

import com.githubcolomboDavide7.connection.AbstractServerConnection;
import com.githubcolomboDavide7.connection.ConnectionFactory;
import com.githubcolomboDavide7.tools.AbstractLogger;

import java.net.ConnectException;
import java.util.List;

public class DynamicHostConfig implements IDynamicHostConfig, Runnable {

    public static IDynamicHostConfig connect() throws ConnectException {
        return new DynamicHostConfig();
    }

    private final AbstractServerConnection myConn;
    private final AbstractLogger logger;

    private DynamicHostConfig() throws ConnectException {
        this.myConn = ConnectionFactory.getServiceConnection("dhcp");
        this.logger = this.myConn.getFileManagerAssociatedToConnection();
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
    public void open() {
        new Thread(this).start();
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.logger.openAndReadTextFile();
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
