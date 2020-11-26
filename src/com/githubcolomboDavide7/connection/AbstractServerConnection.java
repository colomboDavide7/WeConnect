package com.githubcolomboDavide7.connection;

import java.io.*;
import java.net.*;

public abstract class AbstractServerConnection implements IAbstractConnection {

    protected KnownServer properties;
    protected final ServerSocket serverSocket;

    public AbstractServerConnection(KnownServer properties) throws ConnectException {
        try {
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

    public abstract boolean isAvailable();

    public abstract boolean isClosed();

}
