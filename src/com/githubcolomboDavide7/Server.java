package com.githubcolomboDavide7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private ServerSocket socket;
    private Socket acceptedSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean started = false;
    private boolean exit = false;

    public Server(int portNumber){
        try {
            this.socket = new ServerSocket(portNumber);     // creates a new ServerSocket at the specific port number
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void accept(){
        if(!started)
            new Thread(this).start();
        started = true;
    }

    @Override
    public void run() {
        try {
            this.acceptedSocket = socket.accept();          // listen for possible communication
            this.in = new DataInputStream(acceptedSocket.getInputStream());
            this.out = new DataOutputStream(acceptedSocket.getOutputStream());

            while(!exit)
                Thread.sleep(1000);
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        if(args.length != 1)
            System.exit(-1);

        int portNumber = Integer.parseInt(args[0]);
        new Server(portNumber).accept();        // create and running a new Thread that
    }

}
