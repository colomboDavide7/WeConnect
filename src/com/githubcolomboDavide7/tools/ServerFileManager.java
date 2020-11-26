package com.githubcolomboDavide7.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerFileManager extends AbstractFileManager {

    private final String dir = "knownserver/";
    private final String filename;
    private boolean exist = false;

    public ServerFileManager(String filename){
        super();
        this.filename = filename;
    }

    @Override
    public void writeToOrCreate() {
        if(super.toWrite == null)
            return;

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(
                            new File(super.commonPath + this.dir + filename), exist));
            writer.append(super.toWrite);
            writer.append("\n");
            writer.flush();
            writer.close();
            if(!exist)
                exist = true;

        } catch(IOException e) {
            System.out.println("Error log Server connections...");
            System.out.println("Quitting JVM!");
            System.exit(-1);
        }
    }

    @Override
    public List<String> openAndReadTextFile() {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File(super.commonPath + this.dir + this.filename)
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
