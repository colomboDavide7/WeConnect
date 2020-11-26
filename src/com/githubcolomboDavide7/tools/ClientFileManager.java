package com.githubcolomboDavide7.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientFileManager extends AbstractFileManager {

    private final String clientDir = "knownhost/";
    private final String filename;
    private boolean exist = false;

    public ClientFileManager(String filename){
        super();
        this.filename = filename;
    }

    @Override
    public void writeToOrCreate() {
        if(super.toWrite.isEmpty())
            return;

        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter(
                            new File(super.commonPath + this.clientDir + this.filename), exist));
            writer.append(super.toWrite);
            writer.append("\n");
            writer.flush();
            writer.close();

            if(!exist)
                exist = true;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> openAndReadTextFile() {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File(super.commonPath + this.clientDir + this.filename)
            ));
            String line;
            while((line = reader.readLine()) != null)
                lines.add(line);
            reader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return lines;
    }
}
