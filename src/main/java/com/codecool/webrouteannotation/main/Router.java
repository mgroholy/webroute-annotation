package com.codecool.webrouteannotation.main;

import com.codecool.webrouteannotation.main.annotations.WebRoute;
import com.codecool.webrouteannotation.main.data.DummyData;
import com.codecool.webrouteannotation.main.handlers.ResponseHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Router {

    private DummyData dummyDataManager;

    public Router(DummyData dummyData) {
        this.dummyDataManager = dummyData;
    }

    @WebRoute(path="/employees", method=HttpMethod.GET)
    public void getEmployees(HttpExchange exchange) throws IOException {
        ResponseHandler.sendResponse(exchange, dummyDataManager.getEmployees().toString());
    }

    @WebRoute(path="/employees", method=HttpMethod.POST)
    public void addEmployee(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        String name = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        dummyDataManager.addEmployee(name);
        ResponseHandler.sendResponse(exchange, dummyDataManager.getEmployees().toString());
    }

    public void invalidPath(HttpExchange httpExchange, String endpoint) throws IOException {
        String response = "Invalid path. Nothing found on " + endpoint + " endpoint.";
        ResponseHandler.sendResponse(httpExchange, response);
    }
}
