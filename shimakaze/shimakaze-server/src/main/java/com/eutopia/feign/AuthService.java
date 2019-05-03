package com.eutopia.feign;

import com.eutopia.domain.JWTInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("nagato")
public interface AuthService {

    @RequestMapping(value = "/jwt/login", method = RequestMethod.POST)
    String login(@RequestBody JWTInfo jwtInfo);
}
