package com.eutopia.bean;

import lombok.Data;

import java.io.Serializable;

//TODO 应该在common或者客户端
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    private int code = SUCCESS;

    private String msg = "success";

    private T data;

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(Throwable e) {
        this.msg = e.getMessage();
        this.code = FAIL;
    }
}
