package com.noname.interceptor;

import com.noname.util.JWTUtils;
import com.noname.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println(requestUrl(httpServletRequest));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        
    }
    public String requestUrl(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(request.getRequestURL().toString());


        String jwt = request.getHeader("token");
        if (StringUtils.isNotBlank(jwt)) {
            String userInfo = JWTUtils.getFromTokenInfo(jwt);
            sb.append(" userInfo : [" + userInfo + "] \n");
        }
        Map map = request.getParameterMap();
        sb.append(" requestParam : [" + JsonUtil.toJson(map) + "]");

        return sb.toString();
    }

}
