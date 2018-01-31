package com.noname.exception;

import com.noname.bo.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    @ResponseBody
    Result resolverException(Exception ex){

        Result rs = new Result();
        rs.setExceptionStatus(""+ex.getClass()+":"+ex.getMessage());
        return rs;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Result resolverTestException(Exception ex){

        Result rs = new Result();
        rs.setExceptionStatus(""+ex.getClass()+":"+ex.getMessage());
        return rs;
    }
}
