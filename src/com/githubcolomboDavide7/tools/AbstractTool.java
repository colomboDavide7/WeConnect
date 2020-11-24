package com.githubcolomboDavide7.tools;

import java.io.*;
import java.util.*;

public abstract class AbstractTool {

    protected final String commonPath = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/";

    public static boolean existFile(String filename){
        File f = new File(filename);
        return f.exists();
    }

    public abstract void writeToOrCreate();

    public abstract List<String> openAndReadTextFile();

}
