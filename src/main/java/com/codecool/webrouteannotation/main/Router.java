package com.codecool.webrouteannotation.main;

import com.codecool.webrouteannotation.main.annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Router {

    @WebRoute(path="/first")
    public static void first(HttpExchange exchange) throws IOException {
        String firstResponse = "first";
        ResponseSender.sendResponse(exchange, firstResponse);
    }

    @WebRoute(path="/second")
    public static void second(HttpExchange exchange) throws IOException {
        String secondResponse = "second";
        ResponseSender.sendResponse(exchange, secondResponse);
    }
}
