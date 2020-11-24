package com.githubcolomboDavide7.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClientTool extends AbstractTool {

    private final String clientDir = "knownhost/";
    private final String filename;

    public ClientTool(String filename){
        super();
        this.filename = filename;
    }

    @Override
    public void writeToOrCreate() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(
                            new File(super.commonPath + this.clientDir + this.filename)
                    ));
            writer.append("");
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> openAndReadTextFile() {
        return null;
    }
}
