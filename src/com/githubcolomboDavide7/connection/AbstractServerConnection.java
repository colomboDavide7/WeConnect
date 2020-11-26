package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.AbstractFileManager;
import com.githubcolomboDavide7.tools.ServerFileManager;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public abstract class AbstractServerConnection implements IAbstractConnection {

    protected KnownServer properties;
    protected final ServerSocket serverSocket;
    protected final ExecutorService pool;
    protected final AbstractFileManager fileManager;
    //protected List<ClientServerChannel> availableConnections

    public AbstractServerConnection(KnownServer properties) throws ConnectException {
        try {
            this.pool = Executors.newFixedThreadPool(properties.maxHost);
            this.fileManager = new ServerFileManager(properties.IPAddress + ".txt");
            this.properties = properties;
            InetAddress ip = InetAddress.getByName(properties.IPAddress);
            this.serverSocket = new ServerSocket(properties.portNumber, properties.maxHost, ip);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening socket at: " + properties.IPAddress +
                    " with port number " + properties.portNumber);
        }
    }

    public abstract boolean matchMaxSupportedHost(int maxHost);

    public abstract boolean matchServer(KnownServer serverProp);

    public abstract boolean isAvailable();

    public abstract boolean isClosed();

}
