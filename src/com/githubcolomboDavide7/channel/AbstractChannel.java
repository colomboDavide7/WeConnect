package com.githubcolomboDavide7.channel;

import java.io.*;
import java.net.*;

public abstract class AbstractChannel implements Runnable {

    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;

    public AbstractChannel(Socket clientSocket) throws ConnectException {
        try {
            this.clientSocket = clientSocket;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream());
        } catch(IOException e) {
            throw new ConnectException("Error opening channel");
        }
    }

}
