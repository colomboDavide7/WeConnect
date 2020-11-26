package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.channel.ClientServerChannel;
import com.githubcolomboDavide7.tools.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class DHCPConnection extends AbstractServerConnection {

    public DHCPConnection(KnownServer properties) throws ConnectException {
        super(properties);
    }

    @Override
    public AbstractLogger getFileManagerAssociatedToConnection() {
        return super.logger;
    }

    @Override
    public String getConnectionInfo(Socket clientSocket) {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, clientSocket.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(clientSocket.getPort()));
        return AbstractFormatter.formatConnectionInfo(info);
    }

    @Override
    public void closeConnection() throws ConnectException {

    }

    @Override
    public void openConnection() throws ConnectException {
        try {
            System.out.println("[DHCP] Waiting for clients...");
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("[DHCP] Client accepted...");
            super.logger.setConnectionInfoToWrite(getConnectionInfo(clientSocket));
            super.logger.writeToOrCreate();
            super.pool.execute(new ClientServerChannel(clientSocket));
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
        return serverProp == KnownServer.DHCP;
    }

    @Override
    public boolean isAvailable() {
        // TODO - tenere traccia del numero di connessioni attualmente attive.
        return false;
    }

    @Override
    public boolean isClosed() {
        // TODO - poter chiudere la connessione
        return false;
    }

    @Override
    public boolean matchPortNumber(int portNum) {
        return portNum == super.properties.portNumber;
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return ip.equals(super.properties.IPAddress);
    }

}
