package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.channel.ClientServerChannel;
import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientConnection extends AbstractClientConnection {

    public ClientConnection(KnownServer server, AbstractLogger logger) throws ConnectException {
        super(server, logger);
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
    public void openConnection() throws ConnectException {
        System.out.println("[CLIENT] open connection...");
        new Thread(new ClientServerChannel(super.socket, super.logger)).start();
        logger.setConnectionInfoToWrite(this.getFormattedConnectionInfo());
        logger.writeToOrCreate();
        System.out.println("[CLIENT] connection established...");
    }

    @Override
    public boolean matchServer(KnownServer server) {
        return super.server.equalTo(server);
    }

}
