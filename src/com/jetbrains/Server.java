package com.jetbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Define the port on which the server is listening
    public static final int PORT = 8100;
    public Server() throws IOException {
        Integer maxPlayers =2;
        Integer curentPlayers=0;
        ServerSocket serverSocket = null ;
        try {
            serverSocket = new ServerSocket(PORT);
            while (curentPlayers< maxPlayers) {

                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();

                curentPlayers++;
                if(curentPlayers==1) {
                    SocketsSingleton.getInstance().setPlayer1(socket);
                    System.out.println("TEST:" + SocketsSingleton.getInstance().getPlayer1());
                }
                else {
                    SocketsSingleton.getInstance().setPlayer2(socket);
                    System.out.println("TEST:" + SocketsSingleton.getInstance().getPlayer2());
                }
                // Execute the client's request in a new thread
                new ClientThread(socket).start();
            }
            System.out.println("Two players for the game!");
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server ();
    }
}
