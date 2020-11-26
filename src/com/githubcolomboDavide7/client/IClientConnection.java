package com.githubcolomboDavide7.client;

import java.net.*;

public interface IClientConnection {

    void close() throws ConnectException;

    void open() throws ConnectException;

}
