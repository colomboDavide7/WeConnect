package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public abstract class AbstractServerConnection implements IAbstractConnection {

    protected final KnownServer properties;
    protected final ServerSocket serverSocket;
    protected final ExecutorService pool;
    protected final AbstractLogger logger;
    //protected List<ClientServerChannel> availableConnections

    public AbstractServerConnection(KnownServer properties) throws ConnectException {
        try {
            this.pool = Executors.newFixedThreadPool(properties.maxHost);
            this.logger = new ServerLogger(properties.name() + ".txt");
            this.properties = properties;
            InetAddress ip = InetAddress.getByName(properties.IPAddress);
            this.serverSocket = new ServerSocket(properties.portNumber, properties.maxHost, ip);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening socket at: " + properties.IPAddress +
                    " with port number " + properties.portNumber);
        }
    }

    public abstract List<String> getConnections();

    public abstract String getConnectionInfo(Socket clientSocket);

    public abstract boolean matchMaxSupportedHost(int maxHost);

    public abstract boolean matchServer(KnownServer serverProp);

    public abstract boolean isAvailable();

    public abstract boolean isClosed();

}
