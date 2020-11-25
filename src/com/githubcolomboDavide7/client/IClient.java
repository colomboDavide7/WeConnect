package com.githubcolomboDavide7.client;

import java.net.*;
import java.util.*;

public interface IClient extends IClientComparable {

    void close() throws ConnectException;

    String appendHostNameToPath(String path);

    List<String> getEstablishedConnections();

}
