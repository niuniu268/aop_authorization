package com.example.controller;

import com.example.annotation.Access;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Access("admin"  )
    @GetMapping("query")
    public String query1(){

        return "test aop admin";

    }
}
