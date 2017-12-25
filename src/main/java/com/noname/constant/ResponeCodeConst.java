package com.noname.constant;

public enum ResponeCodeConst {
    SUCCESS(200, "访问成功"),
    ERROR(500, "服务器未知错误");

    private int value;
    private String msg;

    private ResponeCodeConst(int value, String msg){
        this.value = value;
        this.msg = msg;
    }

}
