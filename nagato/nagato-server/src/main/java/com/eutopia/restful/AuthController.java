package com.eutopia.restful;

import com.eutopia.domain.JWTInfo;
import com.eutopia.domain.ResultBean;
import com.eutopia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBean<?> login(@RequestBody JWTInfo jwtInfo) throws Exception {
        return new ResultBean<>(authService.login(jwtInfo));
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResultBean<?> authentication(@RequestParam("token") String token) throws Exception {
        return new ResultBean<>(authService.authentication(token));
    }
}
