package com.eutopia.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String menuDescription;

    private Integer sort;
}
