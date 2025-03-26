package com.trantienloi.jobhunter.demo.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantienloi.jobhunter.demo.domain.RestResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        this.delegate.commence(request, response, authException); // mặc định header

        // set body
        response.setContentType("application/json;charset=UTF-8");
        RestResponse<Object> rs = new RestResponse<>();
        rs.setError(authException.getCause().getMessage());
        rs.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        rs.setMessage("Token không hợp lệ (token hết hạn, không đúng định dạng, hoặc không truyền JWT)");
        mapper.writeValue(response.getWriter(), rs);
    }

}
