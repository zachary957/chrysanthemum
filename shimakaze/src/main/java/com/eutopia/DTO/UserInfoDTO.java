package com.eutopia.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private String ip;
}
