//package com.noname.exception;
//
//import org.springframework.boot.autoconfigure.web.AbstractErrorController;
//import org.springframework.boot.autoconfigure.web.ErrorAttributes;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@ControllerAdvice
//@Controller
//public class GlobalErrorHandler extends AbstractErrorController{
//    public GlobalErrorHandler(ErrorAttributes errorAttributes) {
//        super(errorAttributes);
//    }
//
//
//    @ExceptionHandler()
//    @ResponseStatus(HttpStatus.BAD_GATEWAY)
//    public String resolverTest(Exception ex){
//        return ex.getMessage();
//    }
//
//    @Override
//    public String getErrorPath() {
//        return null;
//    }
//}
