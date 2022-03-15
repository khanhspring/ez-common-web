package com.codelaez.ezcommonweb.tracing.webclient;

import com.codelaez.ezcommonweb.tracing.CurrentTracingContextHolder;
import com.codelaez.ezcommonweb.tracing.TracingHeader;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.codelaez.ezcommonweb.tracing.TracingHeader.*;

/**
 * @author khanhspring
 */
public class TracingFilterFunction implements ExchangeFilterFunction {
    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        var addedHeadersRequest = addTracingHeaders(request);
        return next.exchange(addedHeadersRequest);
    }

    private ClientRequest addTracingHeaders(ClientRequest request) {
        var tracingContext = CurrentTracingContextHolder.getContext();
        if (Objects.isNull(tracingContext)) {
            return request;
        }
        return ClientRequest.from(request)
                .headers(httpHeaders -> {
                    httpHeaders.add(TRACE_ID.getHeaderName(), tracingContext.getTraceId());
                    httpHeaders.add(SPAN_ID.getHeaderName(), tracingContext.getSpanId());
                    httpHeaders.add(APPLICATION_NAME.getHeaderName(), tracingContext.getApplicationName());
                    httpHeaders.add(USERNAME.getHeaderName(), tracingContext.getUsername());;
                }).build();
    }
}
