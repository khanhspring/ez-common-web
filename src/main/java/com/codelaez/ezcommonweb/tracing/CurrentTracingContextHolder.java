package com.codelaez.ezcommonweb.tracing;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * @author khanhspring
 */
public class CurrentTracingContextHolder {
    private static final ThreadLocal<TracingContext> threadLocal = new ThreadLocal<>();

    public static void setContext(TracingContext tracingContext) {
        threadLocal.set(tracingContext);
    }

    @NonNull
    public static TracingContext getContext() {
        var ctx = threadLocal.get();
        return Objects.requireNonNullElse(ctx, new TracingContext());
    }
}
