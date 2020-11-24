package com.githubcolomboDavide7.serverSide;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server implements IServer {

    public static IServer open(int portNumber) throws ConnectException {
        return new Server(portNumber);
    }

    private ServerSocket socket;

    private Server(int portNumber) throws ConnectException {
        try {
            this.socket = new ServerSocket(portNumber);
        } catch(IOException e) {
            throw new ConnectException("Error establishing a connection at port " + portNumber);
        }
    }

    @Override
    public boolean isBound() {
        return this.socket.isBound();
    }

    @Override
    public boolean matchPortNumber(int portNumber) {
        return this.socket.getLocalPort() == portNumber;
    }

    @Override
    public void close() throws ConnectException {
        try {
            this.socket.close();
        } catch(IOException e) {
            throw new ConnectException("Error closing the server socket");
        }
    }

    @Override
    public void printHostName() {
        System.out.println(this.socket.getInetAddress().getHostName());
    }

    @Override
    public String appendHostNameToPath(String path) {
        return path + this.socket.getInetAddress().getHostName();
    }

}
