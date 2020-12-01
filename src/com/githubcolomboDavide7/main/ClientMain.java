package com.githubcolomboDavide7.main;

import com.githubcolomboDavide7.client.*;
import com.githubcolomboDavide7.connection.*;
import java.net.*;

public class ClientMain {

    public static void main(String[] args){
        try {
            IClient client = new Client();
            client.open(KnownServer.APP_SERVER);
            //client.open(KnownServer.DHCP);
            //client.open(KnownServer.FIREWALL);
            //client.open(KnownServer.PROXY);
        } catch(ConnectException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

}
