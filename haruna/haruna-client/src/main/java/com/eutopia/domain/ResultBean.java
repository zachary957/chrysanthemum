package com.eutopia.domain;

import lombok.Data;

@Data
public class ResultBean<T> {

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
