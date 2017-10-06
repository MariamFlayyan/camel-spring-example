/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring;

import junit.framework.TestCase;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringCamelTest extends TestCase {

    @Autowired
    private ProducerTemplate producer;

    @Autowired
    private ConsumerTemplate consumer;

    @EndpointInject(ref = "result")
    private MockEndpoint mock;

    @Test
    public void testConsumerTemplate() throws InterruptedException {
        mock.expectedBodiesReceived("Hello World");

        producer.sendBody("seda:start", "Hello World");

        String body = consumer.receiveBody("seda:start", String.class);

        assertEquals("Hello World", body);

        producer.sendBody("seda:foo", body);

        mock.assertIsSatisfied();
    }
}
