package com.server;

import com.server.ServerTask;

public class Application {

    public static void main(String[] args) throws Exception {
        ServerTask server = new ServerTask();
        server.run();
        server.stop();
    }
}
