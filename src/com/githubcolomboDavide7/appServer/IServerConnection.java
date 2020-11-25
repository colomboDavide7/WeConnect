package com.githubcolomboDavide7.appServer;

import java.net.ConnectException;

public interface IServerConnection {

    void close() throws ConnectException;

    void open() throws ConnectException;

}
