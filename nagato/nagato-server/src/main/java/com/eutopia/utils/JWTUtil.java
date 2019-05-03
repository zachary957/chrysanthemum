package com.eutopia.utils;

import com.eutopia.configuration.JWTConfiguration;
import com.eutopia.domain.JWTInfo;
import com.eutopia.utils.helper.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class JWTUtil {

    @Autowired
    private JWTConfiguration jwtConfiguration;

    public String generateToken(JWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, jwtConfiguration.getPriKey(), jwtConfiguration.getExpire());
    }

    public JWTInfo getInfoFromToken(String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return JWTHelper.getInfoFromToken(token, jwtConfiguration.getPubKey());
    }
}
