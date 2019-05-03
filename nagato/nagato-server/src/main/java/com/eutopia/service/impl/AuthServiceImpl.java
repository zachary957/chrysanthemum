package com.eutopia.service.impl;

import com.eutopia.domain.JWTInfo;
import com.eutopia.service.AuthService;
import com.eutopia.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public String login(JWTInfo jwtInfo) throws Exception {
        return jwtUtil.generateToken(jwtInfo);
    }

    @Override
    public JWTInfo authentication(String token) throws Exception {
        return jwtUtil.getInfoFromToken(token);
    }
}
