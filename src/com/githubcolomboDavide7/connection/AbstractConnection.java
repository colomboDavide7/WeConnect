package com.githubcolomboDavide7.connection;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class AbstractConnection {

    protected int portNumber;
    protected InetAddress ipAddress;

    public AbstractConnection(String validIP, int portNum) throws ConnectException {
        try {
            this.ipAddress = InetAddress.getByName(validIP);
            this.portNumber = portNum;
        } catch(UnknownHostException e) {
            e.printStackTrace();
            throw new ConnectException("Unknown host " + validIP);
        }
    }

    public AbstractConnection() {
    }

    public abstract boolean matchPortNumber(int portNum);

    public abstract boolean matchIPAddress(String ip);

}
