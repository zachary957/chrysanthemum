package com.eutopia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootApplication
@RestController
public class NagatoApplication {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public static void main(String[] args) {
        SpringApplication.run(NagatoApplication.class, args);
    }

    @RequestMapping("/getToken")
    public String generateToken() throws Exception {
        JWTInfo jwtInfo = new JWTInfo("114", "514", "1919");
        return jwtTokenUtil.generateToken(jwtInfo);
    }

    @RequestMapping("/getInfo")
    public String getInfo(@RequestParam("token") String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return jwtTokenUtil.getInfoFromToken(token).toString();
    }
}
