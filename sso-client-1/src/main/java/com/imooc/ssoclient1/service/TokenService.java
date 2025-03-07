package com.imooc.ssoclient1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Slf4j
public class TokenService {
    private final RestTemplate simpleRestTemplate;

    public TokenService() {
        simpleRestTemplate = new RestTemplateBuilder()
            .build();
    }

    public JwtToken getToken() {
        Jwt token = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (token != null && token.getExpiresAt().isBefore(Instant.now())) {
            JwtToken jwtToken =  new JwtToken();
            jwtToken.setAccessToken(token.getTokenValue());
            return jwtToken;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", "sso-client-1");
        map.add("client_secret", "edc45ef0-32ca-434f-a9f9-87ca4f80ca3f");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<JwtToken> res = simpleRestTemplate.exchange(
            "http://localhost:8080/auth/realms/imooc/protocol/openid-connect/token",
            HttpMethod.POST,
            request,
            JwtToken.class
        );
        return res.getBody();
    }
}
