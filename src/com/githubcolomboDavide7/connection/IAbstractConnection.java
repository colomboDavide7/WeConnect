package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.*;
import java.net.*;

public interface IAbstractConnection {

    AbstractLogger getFileManagerAssociatedToConnection();

    String getConnectionInfo(Socket clientSocket);

    void closeConnection() throws ConnectException;

    void openConnection() throws ConnectException;

    boolean matchPortNumber(int portNum);

    boolean matchIPAddress(String ip);

}
