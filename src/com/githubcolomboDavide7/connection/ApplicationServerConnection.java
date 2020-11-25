package com.githubcolomboDavide7.connection;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ApplicationServerConnection extends AbstractServerConnection {

    private final int PORT_NUM = 7000;
    private final int MAX_HOST_SUPPORTED = 3;
    private final String IP_ADDRESS = "127.0.0.1";

    public ApplicationServerConnection() throws ConnectException {
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
    public boolean matchMaxSupportedHost(int maxHost) {
        return maxHost == this.MAX_HOST_SUPPORTED;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean matchPortNumber(int portNum) {
        return portNum == this.PORT_NUM;
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return ip.equals(super.ipAddress.getHostAddress());
    }

}
