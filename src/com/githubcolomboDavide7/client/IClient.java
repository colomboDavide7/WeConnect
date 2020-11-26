package com.githubcolomboDavide7.client;

import java.util.*;

public interface IClient extends IClientComparable, IClientConnection {

    String appendHostNameToPath(String path);

    List<String> getEstablishedConnections();

}
