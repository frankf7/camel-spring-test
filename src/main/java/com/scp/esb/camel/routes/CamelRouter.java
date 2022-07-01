/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scp.esb.camel.routes;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Value;


/**
 * A simple Camel REST DSL route with OpenApi API documentation.
 */
public class CamelRouter extends AbstractRouter {


    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    public CamelRouter(String routeId) {
        super(routeId);
    }

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("jetty:http://0.0.0.0:8976").removeHeader(Exchange.HTTP_URI)
                .to("log:?level=info&showbody=true").log("Processing ${id}").to("https://www.baidu.com/")
                .routeId(super.routeId);

        // @formatter:on
    }

}
