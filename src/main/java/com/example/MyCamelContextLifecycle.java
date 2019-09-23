package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.component.servletlistener.CamelContextLifecycle;
import org.apache.camel.component.servletlistener.ServletCamelContext;
import org.apache.camel.spi.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCamelContextLifecycle implements CamelContextLifecycle {
    static private Logger log = LoggerFactory.getLogger(MyCamelContextLifecycle.class);

    public void beforeStart(ServletCamelContext camelContext, Registry registry) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>>   Before Camel Start   <<<<<<<<<<<<<<<<<< ");
    }

    public void afterStart(ServletCamelContext camelContext, Registry registry) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>>   After Camel Start   <<<<<<<<<<<<<<<<<< ");
        for (Route nxtRoute : camelContext.getRoutes()) {
            String nxtUri = nxtRoute.getEndpoint().getEndpointUri();
            log.info(">>>>>>>>>>>>>>>>>>   Next Route URL= " + nxtUri);
            if (nxtUri.startsWith("servlet:") || nxtUri.startsWith("rest:")) {
                log.info(">>>>>>>>>>>>>>>>>>   Adding Event Driven processor   <<<<<<<<<<<<<<<<<< ");
                //Note: Though it does get invoked for servlet:, it is not being invoked for rest:
                nxtRoute.getRouteContext().addEventDrivenProcessor(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        log.info("-------------                 Event Driven processor was Invoked !!!    ");
                    }
                });
            }
            log.info(">>>>>>>>>>>>>>>>>>   Route >>>>> " + nxtRoute.getRouteContext().getRoute().toString());

        }

    }

    public void beforeStop(ServletCamelContext camelContext, Registry registry) throws Exception {

    }

    public void afterStop(ServletCamelContext camelContext, Registry registry) throws Exception {

    }

    public void beforeAddRoutes(ServletCamelContext camelContext, Registry registry) throws Exception {

    }

    public void afterAddRoutes(ServletCamelContext camelContext, Registry registry) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>>   After Add Routes   <<<<<<<<<<<<<<<<<< ");
    }

}
