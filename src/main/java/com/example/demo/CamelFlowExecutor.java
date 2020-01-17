package com.example.demo;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CamelFlowExecutor {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public CamelFlowExecutor(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public <T> T executeFlowByName(T message, String routeName, Class<T> clazz){
        return producerTemplate.requestBody("direct:"+ routeName, message, clazz);
    }

}
