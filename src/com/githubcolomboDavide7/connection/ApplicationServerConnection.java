package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.channel.ClientServerChannel;
import com.githubcolomboDavide7.tools.*;

import java.io.IOException;
import java.net.*;

public class ApplicationServerConnection extends AbstractServerConnection {

    private final int PORT_NUM = 7000;
    private final int MAX_HOST_SUPPORTED = 3;
    private final String IP_ADDRESS = "127.0.0.1";
    private final ServerSocket serverSocket;

    public ApplicationServerConnection() throws ConnectException {
        super();
        try {
            super.portNumber = this.PORT_NUM;
            super.ipAddress = InetAddress.getByName(this.IP_ADDRESS);
            this.serverSocket = new ServerSocket(this.PORT_NUM, this.MAX_HOST_SUPPORTED, this.ipAddress);
        } catch(UnknownHostException e) {
            e.printStackTrace();
            throw new ConnectException("Unknown host " + this.IP_ADDRESS);
        } catch(IOException e) {
            e.printStackTrace();
            throw new ConnectException("Error opening socket at: " + this.IP_ADDRESS + "with port number " + this.PORT_NUM);
        }
    }

    @Override
    public AbstractFileManager getFileManagerAssociatedToConnection() {
        return new ServerFileManager(this.IP_ADDRESS + ".txt");
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
            Socket client = this.serverSocket.accept();
            System.out.println("Connection established...");
            new ClientServerChannel(this, client).start();
        } catch(ConnectException e) {
            e.printStackTrace();
        } catch(IOException e) {
            //
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
        return this.serverSocket.isClosed();
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
