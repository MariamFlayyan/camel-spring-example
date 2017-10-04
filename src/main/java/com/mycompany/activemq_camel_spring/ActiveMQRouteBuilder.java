/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring;

import org.apache.camel.builder.RouteBuilder;

public class ActiveMQRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:start")
            .to("bean:testBean?method=hello")
            .to("stream:out");
    }
}
