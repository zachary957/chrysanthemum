package com.eutopia.service;

import com.eutopia.domain.JWTInfo;

public interface AuthService {

    String login(JWTInfo jwtInfo) throws Exception;

    JWTInfo authentication(String token) throws Exception;
}
