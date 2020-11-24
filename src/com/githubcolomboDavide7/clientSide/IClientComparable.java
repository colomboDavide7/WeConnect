package com.githubcolomboDavide7.clientSide;

public interface IClientComparable {

    boolean matchPortNumber(int portNumber);

    boolean matchIPAddress(String ip);

    boolean isBound();

    boolean isConnected();

}
