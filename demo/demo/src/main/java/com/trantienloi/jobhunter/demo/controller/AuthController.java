package com.trantienloi.jobhunter.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trantienloi.jobhunter.demo.domain.User;
import com.trantienloi.jobhunter.demo.domain.dto.LoginDTO;
import com.trantienloi.jobhunter.demo.domain.dto.ResponseLoginDTO;
import com.trantienloi.jobhunter.demo.service.UserService;
import com.trantienloi.jobhunter.demo.util.SecurityUtil;

import jakarta.validation.Valid;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService,
            SecurityUtil securityUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO)
            throws MethodArgumentNotValidException, BadCredentialsException {

        if (loginDTO.getPassworld() == null || loginDTO.getPassworld() == null) {
            throw new MethodArgumentNotValidException(null, null);
        }
        User user = this.userService.handleGetUserByEmail(loginDTO.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("email khong ton tai");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassworld());
        // xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String accessToken = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseLoginDTO rs = new ResponseLoginDTO();
        rs.setAccessToken(accessToken);
        return ResponseEntity.ok().body(rs);
    }
}
