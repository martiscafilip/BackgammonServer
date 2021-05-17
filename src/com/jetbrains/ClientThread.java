package com.jetbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }



    public void run() {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            if(socket==SocketsSingleton.getInstance().getPlayer1())
            {
                out.println("1");
                out.flush();
            }
            else
            {
                out.println("2");
                out.flush();
            }

            while(true) {

                if(socket==(SocketsSingleton.getInstance().getPlayer1())) {

                    String request = in.readLine();
                    System.out.println(request);
                    out = new PrintWriter(SocketsSingleton.getInstance().getPlayer2().getOutputStream());
                    // Send the response to the oputput stream: server → client
//                    String raspuns = "Hello " + request + "!";
                    out.println(request);
                    out.flush();
                }
                else
                {
                    String request = in.readLine();
                    System.out.println(request);
                    out = new PrintWriter(SocketsSingleton.getInstance().getPlayer1().getOutputStream());
                    // Send the response to the oputput stream: server → client
//                    String raspuns = "Hello " + request + "!";
                    out.println(request);
                    out.flush();

                }


            }

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
