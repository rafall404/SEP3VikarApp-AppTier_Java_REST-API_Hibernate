package org.socketConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args){

        try{
            ServerSocket welcomeSocket = new ServerSocket(9898);

            while(true){
                Socket socket = welcomeSocket.accept();
                System.out.println("Presentation Tier connected ######");
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
