package com.client;

import java.io.IOException;
import java.net.Socket;

public class ClientTask {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 50000);

        System.out.println("Connection established");

        socket.close();
    }

}
