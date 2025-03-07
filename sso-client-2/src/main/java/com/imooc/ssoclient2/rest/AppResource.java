package com.imooc.ssoclient2.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppResource {

    @PostMapping("/hi")
    public String hi() {
        return "Hi";
    }
}
