package com.githubcolomboDavide7.clientSide;

import java.net.ConnectException;

public interface IClient extends IClientComparable {

    void close() throws ConnectException;

    String appendHostNameToPath(String path);

}
