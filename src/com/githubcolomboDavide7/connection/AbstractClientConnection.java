package com.githubcolomboDavide7.connection;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;
    protected final KnownServer server;
    protected final LocalDateTime connectionTime;
    protected final BufferedReader in;
    protected final PrintWriter out;

    public AbstractClientConnection(KnownServer server) throws ConnectException {
        try {
            this.socket = new Socket(server.IPAddress, server.portNumber);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());
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
