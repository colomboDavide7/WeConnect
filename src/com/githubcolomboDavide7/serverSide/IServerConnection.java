package com.githubcolomboDavide7.serverSide;

import java.net.ConnectException;

public interface IServerConnection {

    void close() throws ConnectException;

    void open() throws ConnectException;

}
