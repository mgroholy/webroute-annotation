package com.codecool.webrouteannotation.main.annotations;
import com.codecool.webrouteannotation.main.HttpMethod;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebRoute {

    public String path();
    public HttpMethod method();

}
