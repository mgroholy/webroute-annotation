package com.codecool.webrouteannotation.main;
import java.net.InetSocketAddress;
import com.codecool.webrouteannotation.main.handlers.RequestHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
