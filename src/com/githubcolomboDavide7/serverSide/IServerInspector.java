package com.githubcolomboDavide7.serverSide;

import java.util.*;

public interface IServerInspector {

    List<String> getEstablishedConnections();

    void log(String toLog);

}
