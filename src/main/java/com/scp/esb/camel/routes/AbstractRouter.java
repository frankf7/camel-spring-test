package com.scp.esb.camel.routes;

import org.apache.camel.builder.RouteBuilder;

abstract class AbstractRouter extends RouteBuilder {

    protected final String routeId;

    public AbstractRouter(String routeId) {
        this.routeId = routeId;
    }
}
