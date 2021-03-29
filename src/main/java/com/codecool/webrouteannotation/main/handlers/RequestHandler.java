package com.codecool.webrouteannotation.main.handlers;

import com.codecool.webrouteannotation.main.HttpMethod;
import com.codecool.webrouteannotation.main.Router;
import com.codecool.webrouteannotation.main.annotations.WebRoute;
import com.codecool.webrouteannotation.main.data.DummyData;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestHandler implements HttpHandler {

    private Map<String, Set<Method>> routeHandlers = new HashMap<>();
    private Router router;

    public RequestHandler() {
        sortHandlers();
        DummyData dummyData = new DummyData();
        this.router = new Router(dummyData);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        HttpMethod requestMethod = HttpMethod.getByString(httpExchange.getRequestMethod());

        String path = uri.getPath();
        if(!routeHandlers.containsKey(path)){
            try {
                Method invalidPathHandler = Router.class.getMethod("invalidPath", HttpExchange.class, String.class);
                invalidPathHandler.invoke(router, httpExchange, path);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Set<Method> routeHandlerSet = routeHandlers.get(path);


        for(Method method: routeHandlerSet){
            if(method.getAnnotation(WebRoute.class).method() == requestMethod){
                Method handler = method;
                try{
                    handler.invoke(router, httpExchange);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sortHandlers(){
        Method[] methods = Router.class.getMethods();
        for(Method method: methods){
            if(method.isAnnotationPresent(WebRoute.class)){
                WebRoute webRouteAnnotation = method.getAnnotation(WebRoute.class);
                String path = webRouteAnnotation.path();
                if(routeHandlers.containsKey(path)){
                    routeHandlers.get(path).add(method);
                } else {
                    Set<Method> handlers = new HashSet<>();
                    handlers.add(method);
                    routeHandlers.put(webRouteAnnotation.path(), handlers);
                }
            }
        }
    }

}
