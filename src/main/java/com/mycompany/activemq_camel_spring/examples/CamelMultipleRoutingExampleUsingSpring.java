/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring.examples;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelMultipleRoutingExampleUsingSpring {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("multipleRoutingApplicationContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(applicationContext, false);

        try {
            camelContext.start();
            Thread.sleep(4000);
        } finally {
            camelContext.stop();
            applicationContext.close();
        }

    }
}
