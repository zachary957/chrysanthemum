package com.eutopia;

import com.eutopia.entity.UserInfo;
import com.eutopia.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.eutopia.mapper")
public class TestMyMapper {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testMyMapper() {
        System.out.println(userInfoMapper.existsWithColumn("user_name", 123));
    }
}
