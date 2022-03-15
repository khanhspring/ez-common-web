package com.codelaez.ezcommonweb.webclient;

import com.codelaez.ezcommonweb.webclient.configuration.WebClientRegistration;
import com.codelaez.ezcommonweb.webclient.property.WebClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({
        WebClientProperties.class
})
public class EzWebClientAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "ez.web-client.clients", matchIfMissing = true)
    public WebClientRegistration webClientRegistration(GenericApplicationContext context,
                                                       WebClientProperties properties,
                                                       WebClient.Builder builder) {
        return new WebClientRegistration(context, properties, builder);
    }
}
