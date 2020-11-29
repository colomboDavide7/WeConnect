package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.tools.*;
import java.net.*;

public class ClientServerChannel extends AbstractChannel {

    public ClientServerChannel(Socket clientSocekt, AbstractLogger logger) throws ConnectException {
        super(clientSocekt, logger);
    }

    @Override
    public void run(){
        // TODO - starting communicating with client
        System.out.println("Channel is open...");
    }

}
