package com.samisezgin.finalproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestController {

    @GetMapping("/")
    public String anyone()
    {
        return "hello anyone";
    }
    @GetMapping("/hello")
    public String hello()
    {
        return "hello user";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "hello admin";
    }
}
