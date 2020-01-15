package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("all")
@Component
public class CamelFlowExecutor {


    private final ProducerTemplate producerTemplate;
    private final CamelContext camelContext;
    private final int poolSize;
    private final int maxPoolSize;
    private final int maxQueueSize;


    @Autowired
    public CamelFlowExecutor(ProducerTemplate producerTemplate, CamelContext camelContext) {
        this.producerTemplate = producerTemplate;
        this.camelContext = camelContext;
        this.poolSize = 10;
        this.maxPoolSize = 20;
        this.maxQueueSize = 1000;
    }

    public <T> T executeFlowByName(List<?> params, String routeName, Class<T> clazz){
        return producerTemplate.requestBody("direct:"+ routeName, params, clazz);
    }

}
