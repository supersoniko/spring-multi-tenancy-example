package com.sachareinert.springliquibase.configuration.web;

import com.sachareinert.springliquibase.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {
    private final String DEFAULT_SCHEMA = "cat";
    public JwtUtil jwtUtil;

    public TenantInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void preHandle(WebRequest request) {
            if (TenantStorage.getTenantId() == null) {
                TenantStorage.setTenantId(DEFAULT_SCHEMA);
            }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) {
        TenantStorage.clear();
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) {

    }
}
