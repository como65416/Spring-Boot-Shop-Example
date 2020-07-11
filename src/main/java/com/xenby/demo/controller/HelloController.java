package com.xenby.demo.controller;

import com.xenby.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping(value="/")
    public String index() {
        return "Greetings from Spring Boot.!";
    }

    @GetMapping(value="/hello")
    public String hello() {
        return this.helloService.sayHello("Bob");
    }
}
