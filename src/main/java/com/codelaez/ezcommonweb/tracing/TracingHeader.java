package com.codelaez.ezcommonweb.tracing;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author khanhspring
 */
@Getter
@AllArgsConstructor
public enum TracingHeader {
    TRACE_ID("X-Trace-Id"),
    SPAN_ID("X-Span-Id"),
    APPLICATION_NAME("X-App-Name"),
    USERNAME("X-Username");

    private final String headerName;
}
