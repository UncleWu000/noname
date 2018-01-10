package com.noname.auth.filter;

import com.noname.bo.CSSubject;
import com.noname.constant.CSSubjectConst;
import com.noname.constant.HeaderConst;
import com.noname.util.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenFormAuthenticationFilter extends FormAuthenticationFilter {


    protected static final String AUTHORIZATION_HEADER = "token";
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
            if(!CSSubjectConst.type.ACCESS_TOKEN.equals(csSubject.getTokenType())){
                //response
                return false;
            }

            if(csSubject.getId() == null){
                //
                return false;
            }

            //不是管理员
            if(!csSubject.getClientOrManage().equals(CSSubjectConst.ClientOrManage.MANAGE)){
                return false;
            }
        }

        boolean loggedIn = false;
        if(isLoginRequest(request, response) || isLoggedAttempt(request, response)){
            try {
                loggedIn = executeLogin(request, response);
            }catch (Exception e){
                e.printStackTrace();
                return loggedIn;
            }
        }

        return loggedIn;
    }


    protected boolean isLoggedAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

}
