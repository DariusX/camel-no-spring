package com.example;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        interceptFrom("servlet:*")
                .log("->>>>>>>>>>  Intercepted <<<<<<<<<<<<<<<<<-");

        System.out.println("----------------  MyRouteBuilder --------------------");

        from("timer://foo?fixedRate=true&period=15s")
                .log("...                   Timer                ...");


        from("servlet:hello?matchOnUriPrefix=true")
                .setBody(constant("<b>Hello World </b> "))
                .log("${body}   ${headers}");


        restConfiguration().component("servlet").host("localhost");

        rest("/ping")
                .get()
                .produces("text/plain")
                .route()
                .transform(constant("Ping\n")).endRest();

    }
}
