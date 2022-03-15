package com.codelaez.ezcommonweb.tracing;

/**
 * @author khanhspring
 */
public interface UserTracingExtractor {
    default String getUsername() {
        return null;
    }
}
