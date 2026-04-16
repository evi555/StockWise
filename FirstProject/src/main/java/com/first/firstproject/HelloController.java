package com.first.firstproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello to you";
    }
    @PostMapping("/hello")
    public String HelloPost(@RequestBody String name){
        return "Hello to "+ name;
    }
}
