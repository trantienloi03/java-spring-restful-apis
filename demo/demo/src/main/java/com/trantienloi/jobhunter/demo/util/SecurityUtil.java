package com.trantienloi.jobhunter.demo.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.util.Base64;

@Service
public class SecurityUtil {

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;
    @Value("${trantienloi.jwt.base64-secret}")
    private String jwtKey;
    @Value("${trantienloi.jwt.token-validity-in-seconds}")
    private String jwtExpiration;

    public void createToken(Authentication authentication) {

    }

}
