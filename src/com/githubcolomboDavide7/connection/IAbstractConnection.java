package com.githubcolomboDavide7.connection;

import com.githubcolomboDavide7.tools.AbstractFileManager;

import java.net.ConnectException;

public interface IAbstractConnection {

    AbstractFileManager getFileManagerAssociatedToConnection();

    String getConnectionInfo();

    void closeConnection() throws ConnectException;

    void openConnection() throws ConnectException;

    boolean matchPortNumber(int portNum);

    boolean matchIPAddress(String ip);

}
