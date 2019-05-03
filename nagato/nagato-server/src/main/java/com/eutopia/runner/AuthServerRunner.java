package com.eutopia.runner;

import com.eutopia.configuration.JWTConfiguration;
import com.eutopia.constant.JWTConstants;
import com.eutopia.utils.helper.RSAKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthServerRunner implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JWTConfiguration jwtConfiguration;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.delete(JWTConstants.REDIS_PUB_KEY);
        redisTemplate.delete(JWTConstants.REDIS_PRI_KEY);
        Map<String, byte[]> keyMap = RSAKeyHelper.generateKey(jwtConfiguration.getRsaSecret());
        byte[] priKey = keyMap.get("pub");
        byte[] pubKey = keyMap.get("pri");
        jwtConfiguration.setPubKey(pubKey);
        jwtConfiguration.setPriKey(priKey);
        redisTemplate.opsForValue().set(JWTConstants.REDIS_PRI_KEY, RSAKeyHelper.toHexString(priKey));
        redisTemplate.opsForValue().set(JWTConstants.REDIS_PUB_KEY, RSAKeyHelper.toHexString(pubKey));
    }
}
