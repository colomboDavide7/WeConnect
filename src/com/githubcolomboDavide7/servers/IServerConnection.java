package com.githubcolomboDavide7.servers;

import java.net.ConnectException;

public interface IServerConnection {

    void close() throws ConnectException;

    void open() throws ConnectException;

}
