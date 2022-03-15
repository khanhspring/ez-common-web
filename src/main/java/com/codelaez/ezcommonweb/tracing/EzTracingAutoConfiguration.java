package com.codelaez.ezcommonweb.tracing;

import com.codelaez.ezcommonweb.tracing.configuration.DefaultUserTracingExtractor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EzTracingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserTracingExtractor userTracingExtractor() {
        return new DefaultUserTracingExtractor();
    }
}
