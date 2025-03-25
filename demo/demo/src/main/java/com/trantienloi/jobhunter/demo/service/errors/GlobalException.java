package com.trantienloi.jobhunter.demo.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trantienloi.jobhunter.demo.domain.RestResponse;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IdInvalidException.class) // ở đâu ném ra ngoại lệ IdInvalid ở đây hứng
    public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException IdException) {
        RestResponse<Object> rs = new RestResponse<>();
        rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
        rs.setError(IdException.getMessage());
        rs.setMessage("Id Invalid");
        return ResponseEntity.badRequest().body(rs);
    }
}
