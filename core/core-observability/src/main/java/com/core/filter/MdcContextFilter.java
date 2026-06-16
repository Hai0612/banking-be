package com.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Order(Ordered.HIGHEST_PRECEDENCE + 1)  // chạy ngay sau Spring Security
public class MdcContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        try {
            // Gateway đã inject X-User-Id sau khi parse JWT
            String userId = request.getHeader("X-User-Id");
            if (userId != null) {
                MDC.put("userId", userId);
            }

            // Echo traceId về response header để client debug dễ
            // OTel đã điền traceId vào MDC rồi
            String traceId = MDC.get("traceId");
            if (traceId != null) {
                response.setHeader("X-Trace-Id", traceId);
            }

            chain.doFilter(request, response);
        } finally {
            // BẮT BUỘC — thread pool reuse, không clear = leak sang request khác
            MDC.remove("userId");
        }
    }
}
