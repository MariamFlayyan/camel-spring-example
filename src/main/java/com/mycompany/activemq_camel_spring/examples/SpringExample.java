/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq_camel_spring.examples;

import com.mycompany.activemq_camel_spring.bean.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringExample.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        try {
            TestBean testBean = (TestBean) applicationContext.getBean("testBean");
            LOGGER.info(testBean.hello("Camel and Spring"));
        } finally {
            applicationContext.close();
        }
    }
}
