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
        // expect the message to be received mock
        mock.expectedBodiesReceived("Hello World");

        // send message to seda:start endpoint
        producer.sendBody("seda:start", "Hello World");

        // then consume the message from seda:start
        String body = consumer.receiveBody("seda:start", String.class);

        assertEquals("Hello World", body);

        // send the message again to seda:foo so it would be routed to the mock endpoint so our unit test
        // can complete
        producer.sendBody("seda:foo", body);

        // assert mock got the message
        mock.assertIsSatisfied();
    }
}
