package com.noname.exception;

import com.noname.bo.Result;
import com.noname.util.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DefaultExceptionResolver {

    @ExceptionHandler
    @ResponseBody
    public Result resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) {


        Result rs = new Result();
        rs.setExceptionStatus(ex.getMessage());
        ResponseUtils.writeJson(response, rs);
        return null;
    }
}

