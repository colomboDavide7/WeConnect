package com.githubcolomboDavide7.tools;

import com.githubcolomboDavide7.connection.ConnectionInfo;

import java.io.*;
import java.util.*;

public abstract class AbstractFileManager {

    protected final String commonPath = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/";
    protected Map<ConnectionInfo, String> toWrite = new HashMap<>();

    public static boolean existFile(String filename){
        File f = new File(filename);
        return f.exists();
    }

    public abstract void writeToOrCreate();

    public abstract List<String> openAndReadTextFile();

    public void setConnectionInfoToWrite(Map<ConnectionInfo, String> toWrite){
        this.toWrite = toWrite;
    }

}
