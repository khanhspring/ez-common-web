package com.codelaez.ezcommonweb.tracing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author khanhspring
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TracingContext {
    private String traceId;
    private String spanId;
    private String applicationName;
    private String username;

    public void nextSpan() {
        this.spanId = UUID.randomUUID().toString();
    }

    public void init() {
        this.traceId = UUID.randomUUID().toString();
    }
}
