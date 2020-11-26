package com.githubcolomboDavide7.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;

    public AbstractClientConnection(String validIP, int portNum) throws ConnectException {
        try {
            this.socket = new Socket(validIP, portNum);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + validIP + " with port number " + portNum);
        }
    }

    public AbstractClientConnection(Socket clientSocket){
        this.socket = clientSocket;
    }

    public abstract String appendHostName(String path);

}
