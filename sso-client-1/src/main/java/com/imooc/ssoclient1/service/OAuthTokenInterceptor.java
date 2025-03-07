package com.imooc.ssoclient1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class OAuthTokenInterceptor implements ClientHttpRequestInterceptor {
    private final TokenService tokenService;

    @Override
    public @NonNull
    ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                 @NonNull ClientHttpRequestExecution execution) throws IOException {
        if (request.getURI().getPort() == 8080) {
            return execution.execute(request, body);
        }
        request.getHeaders().add("Authorization", "Bearer " + tokenService.getToken().getAccessToken());
        return execution.execute(request, body);
    }
}
