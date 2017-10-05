/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring.examples;

import com.mycompany.activemq_camel_spring.bean.TestBean;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelActiveMQExample {

    public static void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("testBean", new TestBean());

        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("activemq:queue:start")
                        .to("bean:testBean?method=hello")
                        .to("stream:out");
                }
            });

            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();

            for (int i = 0; i < 5; i++) {
                producerTemplate.sendBody("activemq:queue:start", "body" + i);
            }

            Thread.sleep(1000);

        } finally {
            camelContext.stop();
        }

    }
}
