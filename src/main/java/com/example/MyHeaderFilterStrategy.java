package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This would need to be set to a specific component, for it to work
 */
public class MyHeaderFilterStrategy implements HeaderFilterStrategy {

    static private Logger log = LoggerFactory.getLogger(MyHeaderFilterStrategy.class);

    public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
        log.info("IN --> applyFilterTo   Camel   Headers");
        return false;
    }

    public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
        log.info("IN --> applyFilterTo   External   Headers");
        return false;
    }
}
