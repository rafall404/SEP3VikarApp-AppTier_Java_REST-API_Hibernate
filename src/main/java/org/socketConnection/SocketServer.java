package org.socketConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class SocketServer {
    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(9898);

            System.out.println("Listening on port 9898 "
                    + "Press enter to quit "
                    + "after the next connection.");
            Socket socket = serverSocket.accept();
            System.out.println("A client has connected.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
        System.out.println(response);
        PrintWriter out =
                new PrintWriter(socket.getOutputStream(),
                        true);
        out.print("You have connected to Server" + LocalDate.now());
        out.flush();
        in.close();
        out.close();
            socket.close();


            System.out.println("Quitting...");
            serverSocket.close();

    }
}
