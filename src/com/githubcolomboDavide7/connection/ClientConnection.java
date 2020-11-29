package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientConnection extends AbstractClientConnection {

    public ClientConnection(KnownServer server) throws ConnectException {
        super(server);
    }

    @Override
    public void closeConnection() throws ConnectException {
        try {
            this.socket.close();
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error closing client socket...");
        }
    }

    @Override
    public void openConnection() {
        this.logger.setConnectionInfoToWrite(this.getFormattedConnectionInfo());
        this.logger.writeToOrCreate();
    }

    @Override
    public String getFormattedConnectionInfo() {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, super.server.IPAddress);
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(super.server.portNumber));
        info.put(ConnectionInfo.REQUEST_DATE_TIME, this.connectionStart.toString());
        return AbstractFormatter.formatConnectionInfo(info);
    }

    @Override
    public boolean matchServer(KnownServer server) {
        return super.server.equalTo(server);
    }

}
