package com.example.demo;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

    private final ConsumerService service;

    private Processor processor = exchange -> {
        System.out.println(exchange.getIn().getBody());
    };

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
                .bean(service, "receiveMessage(${in.body}")
                .end()
                .process(processor)
                .end();
    }
}
