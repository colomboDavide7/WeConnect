package com.githubcolomboDavide7.connection;

import java.net.*;

public interface IAbstractConnection {

    void closeConnection() throws ConnectException;

}
