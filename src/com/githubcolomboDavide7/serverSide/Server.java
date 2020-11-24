package com.githubcolomboDavide7.serverSide;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;

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
}
