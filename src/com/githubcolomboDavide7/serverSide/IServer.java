package com.githubcolomboDavide7.serverSide;

import java.net.ConnectException;
import java.util.List;

public interface IServer extends IServerComparable {

    void close() throws ConnectException;

    void printHostName();

    List<String> getEstablishedConnections();

}
