package com.noname.interceptor;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class ParamInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {


        //httpServletRequest.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Enumeration<String> lists =  httpServletRequest.getParameterNames();

        System.out.println("MethodType:"+httpServletRequest.getMethod());
        System.out.println("param:{");
        while(lists.hasMoreElements()){
            String paramName = lists.nextElement();
            String paramValue = httpServletRequest.getParameter(paramName);
            System.out.println(paramName +" : "+ paramValue);
        }
        System.out.println("}");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
//        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
