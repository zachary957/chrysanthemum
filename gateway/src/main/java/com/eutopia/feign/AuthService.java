package com.eutopia.feign;

import com.eutopia.domain.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("nagato")
public interface AuthService {

    @RequestMapping(value = "/jwt/authentication", method = RequestMethod.POST)
    ResultBean<?> authentication(@RequestParam("token") String token);
}
