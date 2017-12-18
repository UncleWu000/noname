package com.noname.entity;

public class Result<T> {

    private String code;
    private String status;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
