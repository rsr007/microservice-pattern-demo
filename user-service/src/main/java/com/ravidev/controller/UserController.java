package com.ravidev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class UserController {

    @GetMapping("/hello")
    public String helloOrder(){
        String msg = "hello from user-service!!";
        return msg;
    }
}
