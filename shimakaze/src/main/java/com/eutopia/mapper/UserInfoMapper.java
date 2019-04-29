package com.eutopia.mapper;

import com.eutopia.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import register.MyMapper;

public interface UserInfoMapper extends MyMapper<UserInfo> {

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS result" +
            " from user_info" +
            " where ${col} = #{var}")
    boolean existsWithColumn(@Param("col")String col, @Param("var")Object var);
}
