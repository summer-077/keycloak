package com.imooc.ssoclient1.config;

import com.imooc.ssoclient1.service.OAuthTokenInterceptor;
import com.imooc.ssoclient1.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

    private final TokenService tokenService;
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .interceptors(oAuthTokenInterceptor())
            .build();
    }

    @Bean
    OAuthTokenInterceptor oAuthTokenInterceptor() {
        return new OAuthTokenInterceptor(tokenService);
    }
}
