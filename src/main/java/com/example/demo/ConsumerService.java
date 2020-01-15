package com.example.demo;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ConsumerService {

    public String receiveMessage(List<BigInteger> into){
        String value = into.get(0).toString();
        return value+value;
    }

    public String emptyMessage(){
        return "This method is deprecated";
    }
}
