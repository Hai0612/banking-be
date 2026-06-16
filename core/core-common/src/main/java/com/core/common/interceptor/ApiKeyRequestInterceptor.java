package com.core.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign Auto Configuration:
 *
 * Tìm tất cả Bean kiểu RequestInterceptor
 *
 * Tìm thấy:
 *
 * ApiKeyRequestInterceptor
 *
 * Feign tự động add vào chain:
 *
 * FeignClient
 *     ↓
 * RequestInterceptor 1
 * RequestInterceptor 2
 * RequestInterceptor 3
 */
// No @Component annotation. This is a simple, reusable class.
public class ApiKeyRequestInterceptor implements RequestInterceptor {

    private final String headerName;
    private final String apiKey;

    // The key is now passed in through the constructor
    public ApiKeyRequestInterceptor(String headerName, String apiKey) {
        this.headerName = headerName;
        this.apiKey = apiKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (headerName != null && apiKey != null) {
            template.header(headerName, apiKey);
        }
    }
}