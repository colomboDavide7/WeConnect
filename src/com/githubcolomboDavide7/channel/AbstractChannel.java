package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.tools.*;
import java.io.*;
import java.net.*;

public abstract class AbstractChannel implements Runnable {

    protected Socket clientSocket;
    protected AbstractLogger logger;
    protected BufferedReader in;
    protected PrintWriter out;

    public AbstractChannel(Socket clientSocket, AbstractLogger logger) throws ConnectException {
        try {
            this.clientSocket = clientSocket;
            this.logger = logger;
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.out = new PrintWriter(this.clientSocket.getOutputStream());
        } catch(IOException e) {
            throw new ConnectException("Error opening channel");
        }
    }

}
