package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "demo/camel")
public class Controller extends CamelFlowExecutor {

    private static final String camelFlow = "test.flow.camel";

    public Controller(ProducerTemplate producerTemplate, CamelContext camelContext) {
        super(producerTemplate, camelContext);
    }

    @GetMapping
    public String endPoint(@RequestParam BigInteger number){
        List<BigInteger> request = new ArrayList<>();
        request.add(number);
        return executeFlowByName(request, camelFlow, String.class);
    }
}
