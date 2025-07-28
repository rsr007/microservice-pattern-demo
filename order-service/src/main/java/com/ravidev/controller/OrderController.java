package com.ravidev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/hello")
    public String helloOrder(){
        String msg = "hello from order-service!";
        return msg;
    }
}
