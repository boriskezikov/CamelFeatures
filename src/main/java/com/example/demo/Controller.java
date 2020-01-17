package com.example.demo;

import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "demo/camel")
public class Controller extends CamelFlowExecutor {


    public Controller(ProducerTemplate producerTemplate) {
        super(producerTemplate);
    }

    @GetMapping
    public BigInteger endPoint(@RequestParam BigInteger number) {
        return executeFlowByName(number, RoutesConstants.testCamelRouting.name(), BigInteger.class);
    }
}
