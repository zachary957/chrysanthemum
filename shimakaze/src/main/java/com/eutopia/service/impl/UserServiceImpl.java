package com.eutopia.service.impl;

import com.eutopia.DTO.UserInfoDTO;
import com.eutopia.mapper.UserInfoMapper;
import com.eutopia.service.UserService;
import exception.CheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public String register(UserInfoDTO userInfoDTO) {
        if (userInfoMapper.existsWithColumn("user_name", userInfoDTO.getUserName())) {
            throw new CheckException("用户已经被注册");
        }
        String firstSalt = UUID.randomUUID().toString().replaceAll("-", "");
        String lastSalt = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            String encryptedPwd = this.getEncryptedPwd(firstSalt, userInfoDTO.getPassword(), lastSalt);
        } catch (UnsupportedEncodingException e) {
            throw new CheckException("系统不支持指定的字符集");
        }

    }

    private String getEncryptedPwd(String firstSalt, String lastSalt, String pwd) throws UnsupportedEncodingException {
        byte[] bytes = (firstSalt + pwd + lastSalt).getBytes(StandardCharsets.UTF_8.name());
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
