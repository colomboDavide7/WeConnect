package com.githubcolomboDavide7.connection;

public enum ConnectionInfo {

    IP_ADDRESS(1), PORT_NUMBER(2), HOST_NAME(3),
    START_DATE_TIME(4), END_DATE_TIME(5), REQUEST_DATE_TIME(6);

    // Start date time & End date time are two enums that are used to store the period of validity
    // for a particular IP address assignment.

    public final int order;

    ConnectionInfo(int order){
        this.order = order;
    }

}
