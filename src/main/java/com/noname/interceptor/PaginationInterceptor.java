package com.noname.interceptor;

import com.github.pagehelper.PageHelper;
import com.noname.annotation.Pagination;
import com.noname.util.StrUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaginationInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        if(isPagination(o)){
            System.out.println("==================================== paging ====================================");
            PageHelper.startPage(getPageNum(httpServletRequest), getPageSize(httpServletRequest));
        }
        System.out.println("pagination param: pageNum:"+getPageNum(httpServletRequest)+ ", pageSize:"+getPageSize(httpServletRequest));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static boolean isPagination(Object o){
        if(o instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod)o;
            Pagination pagination = hm.getMethodAnnotation(Pagination.class);
            if(pagination != null){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static Integer getPageNum(HttpServletRequest request){
        String pageNum = null;
        if((pageNum = request.getParameter("pageNum"))!=null && StrUtils.isNumber(pageNum)){
            return Integer.valueOf(pageNum);
        }else{
            return 1;
        }
    }

    public static Integer getPageSize(HttpServletRequest request){
        String pageSize = null;
        if((pageSize = request.getParameter("pageSize"))!=null && StrUtils.isNumber(pageSize)){
            return Integer.valueOf(pageSize);
        }else{
            return 9999;
        }
    }
}
