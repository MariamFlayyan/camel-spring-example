/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelActiveMQExampleUsingSpring {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(applicationContext, false);

        try {
            camelContext.start();
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            for (int i = 0; i < 5; i++) {
                producerTemplate.sendBody("activemq:queue:start", "body" + i);
            }

            Thread.sleep(1000);
        } finally {
            camelContext.stop();
            applicationContext.close();
        }

    }
}
