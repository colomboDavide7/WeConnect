package com.githubcolomboDavide7.channel;

import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.tools.AbstractFormatter;
import com.githubcolomboDavide7.tools.AbstractLogger;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.HashMap;
import java.util.Map;

public class ClientDHCPChannel extends AbstractChannel {


    public ClientDHCPChannel(Socket clientSocket, AbstractLogger logger) throws ConnectException {
        super(clientSocket, logger);
    }

    @Override
    public void run() {
        System.out.println("Sending info to the client...");
        String msg = this.getInfoToOutputToClient();
        super.logger.setConnectionInfoToWrite(msg);
        super.logger.writeToOrCreate();
        out.println("[DHCP] " + msg);
    }

    private String getInfoToOutputToClient(){
        System.out.println("getting info...");
        //LocalDateTime dateTime = LocalDateTime.now();
        //dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.IP_ADDRESS, KnownServer.APP_SERVER.IPAddress);
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(KnownServer.APP_SERVER.portNumber));
        info.put(ConnectionInfo.HOST_NAME, KnownServer.APP_SERVER.name());
        info.put(ConnectionInfo.START_DATE_TIME, "");
        String msg = AbstractFormatter.formatConnectionInfo(info);
        System.out.println(msg);
        return msg;
    }

}
