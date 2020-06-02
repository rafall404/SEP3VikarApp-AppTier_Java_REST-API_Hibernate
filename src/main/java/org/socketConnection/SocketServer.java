package org.socketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args){

        try{
            ServerSocket welcomeSocket = new ServerSocket(9898);


                Socket socket = welcomeSocket.accept();
                ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

                outToClient.writeObject("You have been connected to the App tier... You may now begin to Consume RestFul Web Services... Congratulations!!!");
                String connection = (String) inFromClient.readObject();
                if(!connection.equals(null))
                {
                    System.out.println("Presentation Tier has been Connected");
                    System.out.println("Terminated Connection");
                }

                inFromClient.close();
                outToClient.close();
                socket.close();


        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
