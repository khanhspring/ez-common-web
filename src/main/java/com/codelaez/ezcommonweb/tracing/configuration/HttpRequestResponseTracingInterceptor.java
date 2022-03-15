package com.codelaez.ezcommonweb.tracing.configuration;

import com.codelaez.ezcommonweb.tracing.CurrentTracingContextHolder;
import com.codelaez.ezcommonweb.tracing.TracingContext;
import com.codelaez.ezcommonweb.tracing.TracingContextExtractor;
import com.codelaez.ezcommonweb.tracing.UserTracingExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author khanhspring
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class HttpRequestResponseTracingInterceptor implements HandlerInterceptor {

    private final UserTracingExtractor userTracingExtractor;
    private  final ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var tracingContext = new TracingContextExtractor()
                .extract(request)
                .orElse(TracingContext.builder()
                        .username(userTracingExtractor.getUsername())
                        .traceId(UUID.randomUUID().toString())
                        .build());

        tracingContext.nextSpan();
        tracingContext.setApplicationName(applicationContext.getId());
        CurrentTracingContextHolder.setContext(tracingContext);

        log.info("Request[A={}, T={}, S={}, U={}]",
                tracingContext.getApplicationName(),
                tracingContext.getTraceId(),
                tracingContext.getSpanId(),
                tracingContext.getUsername());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        var tracingContext = CurrentTracingContextHolder.getContext();
        log.info("Response[A={}, T={}, S={}, U={}]",
                tracingContext.getApplicationName(),
                tracingContext.getTraceId(),
                tracingContext.getSpanId(),
                tracingContext.getUsername());
    }
}
