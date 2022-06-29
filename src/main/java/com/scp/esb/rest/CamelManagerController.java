package com.scp.esb.rest;

import org.apache.camel.Route;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CamelManagerController {

    @Autowired
    SpringBootCamelContext springBootCamelContext;

    @GetMapping(value = "routes")
    public Object getRoutes() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Route> routes = springBootCamelContext.getRoutes();

        for (Route r : routes) {
            Map<String, Object> m = new HashMap<>();
            m.put("routeId", r.getRouteId());
            m.put("ed Base URI", r.getEndpoint().getEndpointBaseUri());
            res.add(m);
        }

        return res;
    }

    @GetMapping(value = "hello")
    public Object hello() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Route> routes = springBootCamelContext.getRoutes();

        for (Route r : routes) {
            Map<String, Object> m = new HashMap<>();
            m.put("routeId", r.getRouteId());
            m.put("ed Base URI", r.getEndpoint().getEndpointBaseUri());
            res.add(m);
        }

        return res;
    }

}
