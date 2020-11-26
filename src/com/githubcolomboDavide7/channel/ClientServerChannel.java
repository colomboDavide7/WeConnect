package com.githubcolomboDavide7.channel;

import java.net.*;

public class ClientServerChannel extends AbstractChannel {

    public ClientServerChannel(Socket clientSocket) throws ConnectException {
        super(clientSocket);
    }

    @Override
    public void run(){
        // TODO - starting communicating with client
        System.out.println("Channel is open...");
    }

}
