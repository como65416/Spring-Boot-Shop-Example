package com.xenby.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    HelloService() {
    }

    public String sayHello(String name) {
        return "hello, " + name;
    }
}
