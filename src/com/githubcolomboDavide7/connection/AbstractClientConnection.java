package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;
    protected final AbstractLogger logger;
    protected final LocalDateTime connectionStart;
    protected final KnownServer server;

    public AbstractClientConnection(KnownServer server) throws ConnectException {
        try {
            this.socket = new Socket(server.IPAddress, server.portNumber);
            this.server = server;
            this.logger = new ClientLogger(this.socket.getInetAddress().getHostAddress() + ".txt");
            this.connectionStart = LocalDateTime.now();
            this.connectionStart.format(DateTimeFormatter.BASIC_ISO_DATE);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + server.IPAddress
                                     + " with port number " + server.portNumber);
        }
    }

    public abstract String getFormattedConnectionInfo();

    public abstract boolean matchServer(KnownServer server);

}
