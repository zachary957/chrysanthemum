package com.eutopia.service.impl;

import com.eutopia.domain.JWTInfo;
import com.eutopia.domain.UserInfoDTO;
import com.eutopia.entity.UserInfo;
import com.eutopia.exception.CheckException;
import com.eutopia.feign.AuthService;
import com.eutopia.mapper.UserInfoMapper;
import com.eutopia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AuthService authService;

    @Override
    public Integer register(UserInfoDTO userInfoDTO) {
        if (userInfoMapper.existsWithColumn("user_name", userInfoDTO.getUserName())) {
            throw new CheckException("用户已经被注册");
        }
        String firstSalt = UUID.randomUUID().toString().replaceAll("-", "");
        String lastSalt = UUID.randomUUID().toString().replaceAll("-", "");
        String encryptedPwd;
        try {
            encryptedPwd = this.getEncryptedPwd(firstSalt, userInfoDTO.getPassword(), lastSalt);
        } catch (UnsupportedEncodingException e) {
            throw new CheckException("系统不支持指定的字符集");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userInfoDTO.getUserName());
        userInfo.setPassword(encryptedPwd);
        userInfo.setFirstSalt(firstSalt);
        userInfo.setLastSalt(lastSalt);
        userInfo.setGmtCreate(new Date());
        userInfo.setIp(userInfoDTO.getIp());
        userInfoMapper.insertSelective(userInfo);
        return userInfo.getId();
    }

    @Override
    public String login(UserInfoDTO userInfoDTO) {
        if (!userInfoMapper.existsWithColumn("user_name", userInfoDTO.getUserName())) {
            throw new CheckException("该用户名不存在");
        }
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userInfoDTO.getUserName());
        UserInfo userInfo = userInfoMapper.selectOneByExample(example);
        String encryptedPwd;
        try {
            encryptedPwd = this.getEncryptedPwd(userInfo.getFirstSalt(), userInfoDTO.getPassword(), userInfo.getLastSalt());
        } catch (UnsupportedEncodingException e) {
            throw new CheckException("系统不支持指定的字符集");
        }
        if (!userInfo.getPassword().equals(encryptedPwd)) {
            throw new CheckException("密码错误");
        }
        return authService.login(new JWTInfo(userInfo.getId() + "", userInfo.getUserName(), userInfo.getPassword()));
    }

    private String getEncryptedPwd(String firstSalt, String lastSalt, String pwd) throws UnsupportedEncodingException {
        byte[] bytes = (firstSalt + pwd + lastSalt).getBytes(StandardCharsets.UTF_8.name());
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
