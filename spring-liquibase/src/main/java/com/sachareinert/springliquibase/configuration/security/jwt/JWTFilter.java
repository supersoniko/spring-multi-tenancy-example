package com.sachareinert.springliquibase.configuration.security.jwt;

import com.sachareinert.springliquibase.configuration.web.TenantStorage;
import com.sachareinert.springliquibase.utils.JwtUtil;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filters incoming requests and sets the tenant context for valid JWTs
 */
public class JWTFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTH_HEADER_PREPEND = "Bearer ";

    private JwtUtil jwtUtil;

    public JWTFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

            String requestTokenHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            String jwtToken = null;

            if (requestTokenHeader != null && requestTokenHeader.startsWith(AUTH_HEADER_PREPEND)) {
                jwtToken = requestTokenHeader.substring(7);
            } else {
                logger.warn("JWT does not begin with Bearer");
            }

            jwtUtil.validateToken(jwtToken);
            TenantStorage.setTenantId(jwtUtil.getTenantTokenFromToken(jwtToken));
        } catch (Exception e) {
            // In a production application this should be handled by an exception translator
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
