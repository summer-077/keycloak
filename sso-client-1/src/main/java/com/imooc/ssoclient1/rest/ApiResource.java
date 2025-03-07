package com.imooc.ssoclient1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiResource {
    private final RestTemplate restTemplate;
    @GetMapping("/hello")
    public String  greeting() {
//        return SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(null, headers);
        return restTemplate.postForObject("http://localhost:8082/client-two/api/hi", entity, String.class);
    }
}
