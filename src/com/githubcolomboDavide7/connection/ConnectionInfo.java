package com.githubcolomboDavide7.connection;

public enum ConnectionInfo {

    IP_ADDRESS(1), PORT_NUMBER(2), HOST_NAME(3),
    START_DATE_TIME(4), END_DATE_TIME(5);

    public final int order;

    ConnectionInfo(int order){
        this.order = order;
    }

}
