package com.codecool.webrouteannotation.main;

import com.codecool.webrouteannotation.main.annotations.WebRoute;
import com.codecool.webrouteannotation.main.data.DummyData;
import com.codecool.webrouteannotation.main.data.Employee;
import com.codecool.webrouteannotation.main.handlers.ResponseHandler;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Router {

    private DummyData dummyDataManager;

    public Router(DummyData dummyData) {
        this.dummyDataManager = dummyData;
    }

    @WebRoute(path="/employees", method=HttpMethod.GET)
    public void getEmployees(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        ResponseHandler.sendResponse(exchange, gson.toJson(dummyDataManager.getEmployees()));
    }

    @WebRoute(path="/employees", method=HttpMethod.POST)
    public void addEmployee(HttpExchange exchange) throws IOException {
        String json = readRequestBody(exchange.getRequestBody());
        Gson gson = new Gson();
        String newName = (String) gson.fromJson(json, HashMap.class).get("name");
        dummyDataManager.addEmployee(newName);
        ResponseHandler.sendResponse(exchange, gson.toJson(dummyDataManager.getEmployees()));
    }

    @WebRoute(path="/employees", method=HttpMethod.PUT)
    public void updateEmployee(HttpExchange httpExchange) throws IOException {
        String json = readRequestBody(httpExchange.getRequestBody());
        Gson gson = new Gson();
        Employee employeeData = gson.fromJson(json, Employee.class);
        int id = employeeData.getId();
        String newName = employeeData.getName();
        dummyDataManager.updateEmployee(id, newName);
        ResponseHandler.sendResponse(httpExchange, gson.toJson(dummyDataManager.getEmployees()));
    }

    @WebRoute(path="/employees", method = HttpMethod.DELETE)
    public void deleteEmployee(HttpExchange httpExchange) throws IOException{
        String json = readRequestBody(httpExchange.getRequestBody());
        Gson gson = new Gson();
        Employee employeeData = gson.fromJson(json, Employee.class);
        int id = employeeData.getId();
        dummyDataManager.deleteEmployee(id);
        ResponseHandler.sendResponse(httpExchange, gson.toJson(dummyDataManager.getEmployees()));
    }

    public void invalidPath(HttpExchange httpExchange, String endpoint) throws IOException {
        String response = "Invalid path. Nothing found on " + endpoint + " endpoint.";
        ResponseHandler.sendResponse(httpExchange, response);
    }

    private String readRequestBody(InputStream body){
        String bodyString = new BufferedReader(
                new InputStreamReader(body, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        return bodyString;
    }
}
