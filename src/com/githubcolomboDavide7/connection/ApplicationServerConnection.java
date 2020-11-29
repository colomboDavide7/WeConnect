package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.channel.*;
import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class ApplicationServerConnection extends AbstractServerConnection {

    public ApplicationServerConnection(KnownServer properties) throws ConnectException {
        super(properties);
    }

    @Override
    public List<String> getConnections() {
        return this.logger.openAndReadTextFile();
    }

    @Override
    public String getConnectionInfo(Socket clientSocket) {
        LocalDateTime connectionTime = LocalDateTime.now();
        connectionTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, clientSocket.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(clientSocket.getLocalPort()));
        info.put(ConnectionInfo.REQUEST_DATE_TIME, connectionTime.toString());
        return AbstractFormatter.formatConnectionInfo(info);
    }

    @Override
    public void closeConnection() throws ConnectException {
        try {
            this.serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error closing Application Server socket");
        }
    }

    @Override
    public void openConnection() {
        try {
            System.out.println("[SERVER] Waiting for clients...");
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("[SERVER] Client accepted...");
            super.pool.execute(new ClientServerChannel(clientSocket, this.logger));
            super.logger.setConnectionInfoToWrite(this.getConnectionInfo(clientSocket));
            super.logger.writeToOrCreate();
        } catch(ConnectException e) {
            e.printStackTrace();
        } catch(IOException e) {
            //
        }
    }

    @Override
    public boolean matchMaxSupportedHost(int maxHost) {
        return maxHost == super.properties.maxHost;
    }

    @Override
    public boolean matchServer(KnownServer serverProp) {
        return serverProp == KnownServer.APP_SERVER;
    }

    @Override
    public boolean isAvailable() {
        // TODO
        return false;
    }

    @Override
    public boolean isClosed() {
        return this.serverSocket.isClosed();
    }

}
