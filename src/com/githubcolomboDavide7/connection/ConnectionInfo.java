package com.githubcolomboDavide7.connection;

public enum ConnectionInfo {

    IP_ADDRESS(1), PORT_NUMBER(2);

    public final int order;

    ConnectionInfo(int order){
        this.order = order;
    }

}
