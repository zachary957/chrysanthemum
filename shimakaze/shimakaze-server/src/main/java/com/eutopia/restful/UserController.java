package com.eutopia.restful;

import com.eutopia.domain.ResultBean;
import com.eutopia.domain.UserInfoDTO;
import com.eutopia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBean<?> register(@RequestBody UserInfoDTO userInfoDTO) {
        return new ResultBean<>(userService.register(userInfoDTO));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBean<?> login(@RequestBody UserInfoDTO userInfoDTO) {
        return new ResultBean<>(userService.login(userInfoDTO));
    }
}
