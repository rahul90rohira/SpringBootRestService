package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {


    @Autowired
    GreetingBeans greetingBeans;

    AtomicLong counter= new AtomicLong();


    @GetMapping("/greeting")
    public GreetingBeans greeting(@RequestParam(value ="name")String name)
    {
        greetingBeans.setId(counter.incrementAndGet());
        greetingBeans.setContent("Hello , I am learning spring boot from "+name);

        return greetingBeans;



    }
}
