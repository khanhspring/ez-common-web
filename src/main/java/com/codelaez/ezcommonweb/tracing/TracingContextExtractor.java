package com.codelaez.ezcommonweb.tracing;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**
 * @author khanhspring
 */
public class TracingContextExtractor {

    public Optional<TracingContext> extract(HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return Optional.empty();
        }
        var traceId = request.getHeader(TracingHeader.TRACE_ID.getHeaderName());
        if (Objects.isNull(traceId)) {
            return Optional.empty();
        }

        var username = request.getHeader(TracingHeader.USERNAME.getHeaderName());

        var tracingContext = TracingContext.builder()
                .traceId(traceId)
                .username(username)
                .build();
        return Optional.of(tracingContext);
    }
}
