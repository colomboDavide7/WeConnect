package com.githubcolomboDavide7.clientSide;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements IClient{

    public static IClient open(String ipAddress, int portNumber) throws ConnectException {
        return new Client(ipAddress, portNumber);
    }

    private Socket socket;

    private Client(String ipAddress, int portNumber) throws ConnectException {
        try {
            this.socket = new Socket(ipAddress, portNumber);
        } catch(IOException e) {
            throw new ConnectException("Error establishing a connection to port number " + portNumber
                                     + "with ip address " + ipAddress);
        }
    }

    @Override
    public boolean matchPortNumber(int portNumber) {
        return this.socket.getPort() == portNumber;
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return this.socket.getLocalAddress().getHostAddress().equals(ip);
    }

    @Override
    public boolean isBound() {
        return this.socket.isBound();
    }

    @Override
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override
    public void close() throws ConnectException {
        try {
            this.socket.close();
        } catch(IOException e) {
            throw new ConnectException("Error closing the connection.");
        }
    }
    
}
