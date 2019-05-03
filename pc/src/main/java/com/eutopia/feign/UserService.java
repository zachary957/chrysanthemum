package com.eutopia.feign;

import com.eutopia.domain.ResultBean;
import com.eutopia.domain.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "shimakaze")
public interface UserService {

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = "application/json")
    ResultBean<String> register(@RequestBody UserInfoDTO userInfoDTO);

    @RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = "application/json")
    ResultBean<String> login(@RequestBody UserInfoDTO userInfoDTO);
}
