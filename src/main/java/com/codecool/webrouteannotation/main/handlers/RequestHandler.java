package com.codecool.webrouteannotation.main.handlers;

import com.codecool.webrouteannotation.main.Router;
import com.codecool.webrouteannotation.main.annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

public class RequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        Method[] methods = Router.class.getMethods();
        for(Method method: methods){
            if (method.isAnnotationPresent(WebRoute.class)) {
                WebRoute webRoute = method.getAnnotation(WebRoute.class);
                String path = webRoute.path();
                if(uri.getPath().equals(path)){
                    try {
                        method.invoke(null, httpExchange);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
