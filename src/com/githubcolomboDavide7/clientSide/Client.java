package com.githubcolomboDavide7.clientSide;

import com.githubcolomboDavide7.connection.ConnectionInfo;
import com.githubcolomboDavide7.tools.AbstractFileManager;
import com.githubcolomboDavide7.tools.AbstractFormatter;
import com.githubcolomboDavide7.tools.ClientFileManager;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements IClient{

    public static IClient open(String ipAddress, int portNumber) throws ConnectException {
        return new Client(ipAddress, portNumber);
    }

    private final Socket socket;
    private final AbstractFileManager clientTool;

    private Client(String ipAddress, int portNumber) throws ConnectException {
        try {
            this.socket = new Socket(ipAddress, portNumber);
            this.clientTool = new ClientFileManager(this.socket.getInetAddress().getHostName() + ".txt");
            this.clientTool.setConnectionInfoToWrite(getConnectionInfo());
            this.clientTool.writeToOrCreate();
        } catch(IOException e) {
            throw new ConnectException("Error establishing a connection to port number " + portNumber
                                     + "with ip address " + ipAddress);
        }
    }

    private String getConnectionInfo() {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, this.socket.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(this.socket.getPort()));
        return AbstractFormatter.formatConnectionInfo(info);
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

    @Override
    public String appendHostNameToPath(String path) {
        return path + this.socket.getInetAddress().getHostName();
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.clientTool.openAndReadTextFile();
    }

    @Override
    public String toString(){
        return this.socket.getInetAddress().getHostAddress() + "\t" + this.socket.getPort();
    }

}
