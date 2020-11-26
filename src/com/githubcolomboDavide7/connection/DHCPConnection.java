package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class DHCPConnection extends AbstractServerConnection {

    public DHCPConnection(KnownServer properties) throws ConnectException {
        super(properties);
    }

    @Override
    public AbstractFileManager getFileManagerAssociatedToConnection() {
        return null;
    }

    @Override
    public String getConnectionInfo() {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, super.properties.IPAddress);
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(super.properties.portNumber));
        return AbstractFormatter.formatConnectionInfo(info);
    }

    @Override
    public void closeConnection() throws ConnectException {

    }

    @Override
    public void openConnection() throws ConnectException {

    }

    @Override
    public boolean matchMaxSupportedHost(int maxHost) {
        return maxHost == super.properties.maxHost;
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
