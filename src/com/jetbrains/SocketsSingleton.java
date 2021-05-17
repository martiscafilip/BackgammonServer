package com.jetbrains;

import java.net.Socket;

public class SocketsSingleton {
    // static variable single_instance of type Singleton
    private static SocketsSingleton single_instance = null;

    // variable of type String
    public Socket player1;
    public Socket player2;

    private SocketsSingleton()
    {

    }

    public static SocketsSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new SocketsSingleton();

        return single_instance;
    }

    public Socket getPlayer1() {
        return player1;
    }

    public Socket getPlayer2() {
        return player2;
    }

    public void setPlayer1(Socket player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Socket player2) {
        this.player2 = player2;
    }
}
