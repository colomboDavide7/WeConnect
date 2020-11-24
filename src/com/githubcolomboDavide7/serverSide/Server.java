package com.githubcolomboDavide7.serverSide;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements IServer, Runnable {

    public static IServer open(int portNumber) throws ConnectException {
        return new Server(portNumber);
    }

    private final ServerSocket serverSocket;
    private final AbstractFileManager serverFileManager;

    private Server(int portNumber) throws ConnectException {
        try {
            this.serverSocket = new ServerSocket(portNumber);
            this.serverFileManager = new ServerFileManager(this.serverSocket.getInetAddress().getHostAddress() + ".txt");

            // Starting a Thread that listen to possible clients that wants to connect
            new Thread(this).start();

        } catch(IOException e) {
            throw new ConnectException("Error establishing a connection at port " + portNumber);
        }
    }

    @Override
    public void run() {
        while(!this.serverSocket.isClosed()){
            try {
                Socket client = this.serverSocket.accept();
                this.serverFileManager.setConnectionInfoToWrite(createConnectionInfo(client));
                this.serverFileManager.writeToOrCreate();
                Thread.sleep(1000);
            } catch(IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private Map<ConnectionInfo, String> createConnectionInfo(Socket client){
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, client.getInetAddress().getHostAddress());
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(this.serverSocket.getLocalPort()));
        return info;
    }

    @Override
    public boolean isBound() {
        return this.serverSocket.isBound();
    }

    @Override
    public boolean matchPortNumber(int portNumber) {
        return this.serverSocket.getLocalPort() == portNumber;
    }

    @Override
    public void close() throws ConnectException {
        try {
            this.serverSocket.close();
        } catch(IOException e) {
            throw new ConnectException("Error closing the server socket");
        }
    }

    @Override
    public void printHostName() {
        System.out.println(this.serverSocket.getInetAddress().getHostName());
    }

    @Override
    public List<String> getEstablishedConnections() {
        return this.serverFileManager.openAndReadTextFile();
    }

}
