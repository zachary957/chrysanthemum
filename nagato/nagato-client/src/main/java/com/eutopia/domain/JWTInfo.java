package com.eutopia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JWTInfo implements Serializable {

    private String userId;

    private String userName;

    private String password;
}
