package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractClientConnection implements IAbstractConnection {

    protected final Socket socket;
    protected final KnownServer server;
    protected final LocalDateTime connectionTime;
    protected final AbstractLogger logger;

    public AbstractClientConnection(KnownServer server, AbstractLogger logger) throws ConnectException {
        try {
            this.socket = new Socket(server.IPAddress, server.portNumber);
            this.server = server;
            this.logger = logger;
            this.connectionTime = LocalDateTime.now();
            this.connectionTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening Client Socket at: " + server.IPAddress
                                     + " with port number " + server.portNumber);
        }
    }

    protected String getFormattedConnectionInfo() {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, server.IPAddress);
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(server.portNumber));
        info.put(ConnectionInfo.REQUEST_DATE_TIME, connectionTime.toString());
        info.put(ConnectionInfo.HOST_NAME, server.name());
        return AbstractFormatter.formatConnectionInfo(info);
    }

    public abstract boolean matchServer(KnownServer server);

}
