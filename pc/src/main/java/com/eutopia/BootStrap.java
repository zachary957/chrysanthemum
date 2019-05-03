package com.eutopia;

import com.eutopia.domain.UserInfoDTO;
import com.eutopia.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class BootStrap {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @GetMapping("/testRegister")
    public String testFeign() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName("hello World");
        userInfoDTO.setPassword("114514");
        userInfoDTO.setIp("127.0.0.1");
        return userService.register(userInfoDTO).getMsg();
    }

    @GetMapping("/testLogin")
    public String testLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName(userName);
        userInfoDTO.setPassword(password);
        return userService.login(userInfoDTO).getMsg() + " " + userService.login(userInfoDTO).getData();
    }
}
