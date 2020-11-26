package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClientConnection extends AbstractClientConnection {

    public ClientConnection(String validIP, int portNum) throws ConnectException {
        super(validIP, portNum);
    }

    public ClientConnection(Socket clientSocket){
        super(clientSocket);
    }

    @Override
    public String appendHostName(String path) {
        return path + this.socket.getInetAddress().getHostAddress();
    }

    @Override
    public AbstractFileManager getFileManagerAssociatedToConnection() {
        return super.fileManager;
    }

    @Override
    public String getConnectionInfo() {
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, super.socket.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(super.serverPort));
        return AbstractFormatter.formatConnectionInfo(info);
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
        //AbstractServerConnection serverConn = ConnectionFactory.getServiceConnection("app_server");
        //new ClientServerChannel(serverConn, this).start();
    }

    @Override
    public boolean matchPortNumber(int portNum) {
        return portNum == super.socket.getPort();
    }

    @Override
    public boolean matchIPAddress(String ip) {
        return ip.equals(super.socket.getInetAddress().getHostAddress());
    }

}
