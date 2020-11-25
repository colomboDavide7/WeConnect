package com.githubcolomboDavide7.connection;

import java.net.ConnectException;

public abstract class AbstractServerConnection extends AbstractConnection {

    public AbstractServerConnection() {
        super();
    }

    public abstract boolean matchMaxSupportedHost(int maxHost);

    public abstract boolean isAvailable();

    public abstract boolean isClosed();

}
