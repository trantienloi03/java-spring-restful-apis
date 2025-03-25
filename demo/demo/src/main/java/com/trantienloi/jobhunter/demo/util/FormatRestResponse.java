package com.trantienloi.jobhunter.demo.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.trantienloi.jobhunter.demo.domain.RestResponse;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {
    // ghi đè lại body
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // đều được ghi đè
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, // body trả về
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse responServlet = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = responServlet.getStatus();
        RestResponse<Object> rs = new RestResponse<>();
        rs.setStatusCode(statusCode);
        if (statusCode >= 400) {
            // case error
            return body;
        } else {
            // case success
            rs.setData(body);
            rs.setMessage("CALL API SUCCESS");
        }
        return rs;
    }

}
