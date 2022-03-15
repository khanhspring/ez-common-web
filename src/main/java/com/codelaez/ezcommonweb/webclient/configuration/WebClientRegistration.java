package com.codelaez.ezcommonweb.webclient.configuration;

import com.codelaez.ezcommonweb.tracing.webclient.TracingFilterFunction;
import com.codelaez.ezcommonweb.webclient.property.WebClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

/**
 * @author khanhspring
 */
@RequiredArgsConstructor
public class WebClientRegistration {
    private final GenericApplicationContext context;
    private final WebClientProperties properties;
    private final WebClient.Builder builder;

    @PostConstruct
    public void register() {
        if (ObjectUtils.isEmpty(properties.getClients())) {
            return;
        }

        for (var client : properties.getClients()) {
            var webClientBuilder = WebClient.builder().baseUrl(client.getBaseUrl());

            if (!ObjectUtils.isEmpty(client.getHeaders())) {
                webClientBuilder.defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.addAll(client.getHeaders());
                });
            }
            webClientBuilder.filter(new TracingFilterFunction());
            var webClient = webClientBuilder.build();
            context.registerBean(client.getId(), WebClient.class, () -> webClient);
        }
    }
}
