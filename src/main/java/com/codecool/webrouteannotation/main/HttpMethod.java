package com.codecool.webrouteannotation.main;

import java.util.Arrays;

public enum HttpMethod {

    GET,
    POST,
    PUT,
    DELETE;

    public static HttpMethod getByString(String method){
        HttpMethod[] httpMethods = HttpMethod.values();
        return Arrays.stream(httpMethods).filter(httpMethod -> httpMethod.toString().equals(method)).findFirst().orElse(null);

    }
}
