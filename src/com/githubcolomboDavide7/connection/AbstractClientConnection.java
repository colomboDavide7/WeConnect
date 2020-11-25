package com.githubcolomboDavide7.connection;

import java.net.ConnectException;

public abstract class AbstractClientConnection extends AbstractConnection {

    public AbstractClientConnection(String validIP, int portNum) throws ConnectException {
        super(validIP, portNum);
    }

    public abstract String appendHostName(String path);

}
