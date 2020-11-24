package com.githubcolomboDavide7.clientSide;

import java.net.*;
import java.util.*;

public interface IClient extends IClientComparable {

    void close() throws ConnectException;

    String appendHostNameToPath(String path);

    List<String> getEstablishedConnections();

}
