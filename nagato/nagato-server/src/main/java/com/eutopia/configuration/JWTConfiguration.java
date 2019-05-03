package com.eutopia.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JWTConfiguration {

    @Value("${jwt.rsa-secret}")
    private String rsaSecret;

    @Value("${jwt.expire}")
    private Integer expire;

    @Value("${jwt.token-header}")
    private String tokenHeader;

    private byte[] priKey;

    private byte[] pubKey;
}
