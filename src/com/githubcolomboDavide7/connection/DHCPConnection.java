package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.AbstractFileManager;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DHCPConnection extends AbstractServerConnection {

    private final int PORT_NUM = 5000;
    private final int MAX_HOST_SUPPORTED = 3;
    private final String IP_ADDRESS = "127.0.0.4";

    public DHCPConnection() throws ConnectException {
        super();
        super.portNumber = this.PORT_NUM;
        try {
            super.ipAddress = InetAddress.getByName(this.IP_ADDRESS);
        } catch(UnknownHostException e) {
            e.printStackTrace();
            throw new ConnectException("Unknown host " + this.IP_ADDRESS);
        }
    }

    @Override
    public AbstractFileManager getFileManagerAssociatedToConnection() {
        return null;
    }

    @Override
    public void closeConnection() throws ConnectException {

    }

    @Override
    public void openConnection() throws ConnectException {

    }

    @Override
    public boolean matchMaxSupportedHost(int maxHost) {
        return maxHost == this.MAX_HOST_SUPPORTED;
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
        return portNum == this.PORT_NUM;
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return ip.equals(this.IP_ADDRESS);
    }

}
