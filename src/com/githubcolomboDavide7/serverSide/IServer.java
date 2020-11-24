package com.githubcolomboDavide7.serverSide;

import java.net.ConnectException;

public interface IServer extends IServerComparable {

    void close() throws ConnectException;

    void printHostName();

    String appendHostNameToPath(String path);

}
