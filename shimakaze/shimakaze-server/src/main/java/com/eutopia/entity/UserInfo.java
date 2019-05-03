package com.eutopia.entity;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserInfo {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String userName;

    private String password;

    private String firstSalt;

    private String lastSalt;

    private Boolean status;

    @ColumnType(column = "gmt_create", jdbcType = JdbcType.TIMESTAMP)
    private Date gmtCreate;

    @ColumnType(column = "gmt_modified", jdbcType = JdbcType.TIMESTAMP)
    private Date gmtModified;

    private String ip;
}
