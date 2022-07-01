package com.scp.esb.rest.controller;

import com.scp.esb.camel.routes.StopOtherRouter;
import org.apache.camel.Route;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            m.put("desc", r.getRouteDescription());
            m.put("status", r.getEndpoint().getCamelContext().getStatus());
            res.add(m);
        }

        return res;
    }

    @GetMapping(value = "add")
    public Object add(@RequestParam String routeId, String reference) {
        try {
            Class<?> cls = Class.forName(reference);
            RoutesBuilder o = (RoutesBuilder) cls.getDeclaredConstructor(new Class[]{String.class}).newInstance(routeId);
            springBootCamelContext.addRoutes(o);
        } catch (Exception e) {
            return "失败 " + e;
        }
        return "成功添加Route";
    }

    @GetMapping(value = "stop")
    public Object stop(@RequestParam String routeId) {
        try {
            springBootCamelContext.addRoutes(new StopOtherRouter(routeId));
            return "删除成功 " + routeId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "删除异常 " + routeId;
    }
}
