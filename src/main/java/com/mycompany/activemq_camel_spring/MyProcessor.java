/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

public class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setBody("HeartBeat " + new Date());
    }
}
