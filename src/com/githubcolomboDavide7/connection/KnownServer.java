package com.githubcolomboDavide7.connection;

public enum KnownServer {

    DHCP("127.0.0.1", 5000),
    PROXY("127.0.0.5", 4000),
    FIREWALL("127.0.0.3", 3000),
    APP_SERVER("127.0.0.1", 7000);

    public final String IPAddress;
    public final int portNumber;
    public final int maxHost;

    KnownServer(String IPAddress, int portNumber){
        this.IPAddress = IPAddress;
        this.portNumber = portNumber;
        this.maxHost = 3;
    }

    public static KnownServer get(String target){
        for(KnownServer s : KnownServer.values())
            if(target.equalsIgnoreCase(s.name()))
                return s;
        throw new IllegalArgumentException("No server that correspond to " + target);
    }

}
