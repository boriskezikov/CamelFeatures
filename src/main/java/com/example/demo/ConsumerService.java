package com.example.demo;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ConsumerService {

    public BigInteger receiveMessage(BigInteger value) {
        return BigInteger.ONE;
    }

    public String emptyMessage() {
        return null;
    }
}
