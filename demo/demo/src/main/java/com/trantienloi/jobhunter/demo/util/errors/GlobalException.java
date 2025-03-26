package com.trantienloi.jobhunter.demo.util.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trantienloi.jobhunter.demo.domain.RestResponse;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = {
            UsernameNotFoundException.class,
            BadCredentialsException.class }) // ở đâu ném ra ngoại lệ IdInvalid ở đây hứng
    public ResponseEntity<RestResponse<Object>> handleIdException(Exception ex) {
        RestResponse<Object> rs = new RestResponse<>();
        rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
        rs.setError(ex.getMessage());
        rs.setMessage("Exception occurs....");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // ở đâu ném ra ngoại lệ IdInvalid ở đây hứng
    public ResponseEntity<RestResponse<Object>> validationerro(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        RestResponse<Object> rs = new RestResponse<>();
        rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
        rs.setError(ex.getBody().getDetail());
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            String error = fieldError.getDefaultMessage();
            errors.add(error);
        }
        rs.setMessage(errors);
        return ResponseEntity.badRequest().body(rs);
    }
}
