package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.channel.*;
import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;

public class ClientConnection extends AbstractClientConnection{

    private final Socket socket;

    public ClientConnection(String validIP, int portNum) throws ConnectException {
        super(validIP, portNum);
        try {
            this.socket = new Socket(validIP, portNum);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + validIP + " with port number " + portNum);
        }
    }

    @Override
    public String appendHostName(String path) {
        return path + this.socket.getInetAddress().getHostName();
    }

    @Override
    public AbstractFileManager getFileManagerAssociatedToConnection() {
        return new ClientFileManager(this.socket.getInetAddress().getHostName() + ".txt");
    }

    @Override
    public void closeConnection() throws ConnectException {
        try {
            this.socket.close();
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error closing client socket...");
        }
    }

    @Override
    public void openConnection() throws ConnectException {
        new ClientServerChannel(this, this.socket).start();
    }

    @Override
    public boolean matchPortNumber(int portNum) {
        return portNum == super.portNumber;
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return ip.equals(super.ipAddress.getHostAddress());
    }
}
