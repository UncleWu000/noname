package com.noname.interceptor;

import com.noname.entity.Admin;
import com.noname.mapper.AdminMapper;
import com.noname.util.EncrypUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class RequestInterceptor implements HandlerInterceptor {

    @Resource
    AdminMapper adminMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        final String token = (String)httpServletRequest.getHeader("token");
        System.out.println(token+"==============================");
        List<Admin> admins = adminMapper.selectAll();
        Admin admin = admins.stream().filter(s -> {
            try {
                return EncrypUtils.encryp(s.getUsername(), s.getPassword()).equals(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList()).get(0);

//        return true;

        if(admin!=null){
            httpServletRequest.getSession().setAttribute("user", admin);
            return true;
        }else{
            throw new Exception("token 错误");

        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("验证完毕");
    }
}
