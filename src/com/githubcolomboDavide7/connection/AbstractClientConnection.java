package com.githubcolomboDavide7.connection;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;
    protected final KnownServer server;
    protected final LocalDateTime connectionTime;

    public AbstractClientConnection(KnownServer server) throws ConnectException {
        try {
            this.socket = new Socket(server.IPAddress, server.portNumber);
            this.server = server;
            this.connectionTime = LocalDateTime.now();
            this.connectionTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + server.IPAddress
                                     + " with port number " + server.portNumber);
        }
    }

    public abstract String getFormattedConnectionInfo();

    public abstract boolean matchServer(KnownServer server);

}
