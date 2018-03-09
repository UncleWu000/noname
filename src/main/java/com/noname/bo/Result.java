package com.noname.bo;

import com.noname.constant.ResultConst;

public class Result {

    private String status;

    private String code;

    private String msg;

    public Result(){
        this.status = ResultConst.status.TRUE;
        this.code = ResultConst.code.SUCCESS;
    };

    public Result(boolean bool){
        if(bool){
            this.status = ResultConst.status.TRUE;
            this.code = ResultConst.code.SUCCESS;
        }else{
            this.status = ResultConst.status.FALSE;
            this.code = ResultConst.code.EXCEPTION;
        }
    }

    public Result(String status, String code, String msg){
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg){
        this();
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setExceptionStatus(){
        this.status = ResultConst.status.FALSE;
        this.code = ResultConst.code.EXCEPTION;
    }

    public void setExceptionStatus(String msg) {
        this.status = ResultConst.status.FALSE;
        this.code = ResultConst.code.EXCEPTION;
        this.msg = msg;
    }
}
