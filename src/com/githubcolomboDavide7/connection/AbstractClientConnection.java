package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.AbstractFileManager;
import com.githubcolomboDavide7.tools.ClientFileManager;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;
    protected final AbstractFileManager fileManager;
    protected final int serverPort;

    public AbstractClientConnection(String validIP, int portNum) throws ConnectException {
        try {
            this.socket = new Socket(validIP, portNum);
            this.serverPort = portNum;
            this.fileManager = new ClientFileManager(this.socket.getInetAddress().getHostAddress() + ".txt");
            this.fileManager.setConnectionInfoToWrite(this.getConnectionInfo());
            this.fileManager.writeToOrCreate();
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + validIP + " with port number " + portNum);
        }
    }

    public AbstractClientConnection(Socket clientSocket){
        this.socket = clientSocket;
        this.serverPort = clientSocket.getLocalPort();
        this.fileManager = new ClientFileManager(this.socket.getInetAddress().getHostAddress() + ".txt");
    }

    public abstract String appendHostName(String path);

}
