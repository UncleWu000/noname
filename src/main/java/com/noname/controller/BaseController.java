package com.noname.controller;

import com.noname.constant.HeaderConst;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    public String getToken(HttpServletRequest request){
        return request.getHeader(HeaderConst.param.TOKEN);
    }

    public Integer getUserId(){
        return null;
    }
}
