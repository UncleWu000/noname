package com.noname.auth.filter;

import com.auth0.jwt.JWT;
import com.noname.bo.CSSubject;
import com.noname.constant.HeaderConst;

import com.noname.util.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(((HttpServletRequest)request).getRequestURI());
        String token = ((HttpServletRequest) request).getHeader(HeaderConst.param.TOKEN);
        System.out.println("token -> "+token);

        if(StringUtils.isBlank(token)){
            //response 4010
            return false;
        }

        if(!!JWTUtils.isTokenLegal(token)){
            //response 4011
            return false;
        }

        if(JWTUtils.isTokenExpired(token)){
            //response 4012
            return false;
        }

        CSSubject csSubject = JWTUtils.getSubject(token);
        if(null == csSubject){
            return false;
        }
        return super.onAccessDenied(request,response);
    }
}
