package com.scp.esb.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/hello")
                .get().route()
                .transform().constant("Hello World").routeId("BaseRoute");

    }
}
