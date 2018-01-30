package com.noname.exception;

import com.noname.bo.Result;
import com.noname.util.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class DefaultExceptionResolver extends DefaultHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        Result rs = new Result();
        rs.setExceptionStatus(ex.getMessage());
        ResponseUtils.writeJson(response, rs);
        return null;
    }
}

