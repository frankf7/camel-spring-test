package com.scp.esb.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StopOtherRouter extends AbstractRouter {
    public StopOtherRouter(String routeId) {
        super(routeId);
    }

    @Override
    public void configure() throws Exception {
        from("direct:stop").routeId(routeId)
                .to("mock:stop")
                .process(new Processor() {
                    Thread stop;

                    @Override
                    public void process(final Exchange exchange) throws Exception {
                        // stop this route using a thread that will stop
                        // this route gracefully while we are still running
                        if (stop == null) {
                            stop = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        exchange.getContext().getRouteController().stopRoute(routeId);
                                    } catch (Exception e) {
                                        // ignore
                                    }
                                }
                            };
                        }

                        // start the thread that stops this route
                        stop.start();
                    }
                }).to("mock:done");
    }
}
