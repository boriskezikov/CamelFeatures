package com.example.demo;

import com.netflix.config.DynamicPropertyFactory;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CamelRoutes.class);

    private static final DynamicPropertyFactory propertyFactory = DynamicPropertyFactory.getInstance();

    private static final String LOG_MESSAGE = "\\n Routing to ${routeId} \\n exchangeId: ${exchangeId}\\n body: ${body}";

    private final ConsumerService service;

    private static final Predicate isMessagingActive = m -> propertyFactory.getBooleanProperty("messaging-active", false).get();

    public CamelRoutes(ConsumerService service) {
        this.service = service;
    }

    @Override
    public void configure() {
        camelRoutes();
    }

    private void camelRoutes() {
        from("direct:" + RoutesConstants.testCamelRouting.name())
                .routeId(RoutesConstants.testCamelRouting.name())
                .log(LoggingLevel.INFO, LOG, LOG_MESSAGE)
                .choice()
                .when(isMessagingActive)
                .process(ex->{
                    ex.getIn();
                })
                    .bean(service, "receiveMessage(${in.body})")
                .endChoice()
                .otherwise()
                    .to("direct:" + RoutesConstants.emptyMessageRoute.name())
                .endChoice()
                .end()
                .end();


        from("direct:" + RoutesConstants.emptyMessageRoute.name())
                .routeId(RoutesConstants.emptyMessageRoute.name())
                .log(LoggingLevel.INFO, LOG, LOG_MESSAGE)
                .bean(service, "emptyMessage()")
                .end();

    }
}
