package com.noname.aspect;


import com.github.pagehelper.PageHelper;
import com.noname.bo.page.PageParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageAspect {

//    @Around("execution (* com.noname.mapper.*.*(..))")
//    public Object pageAspect(ProceedingJoinPoint joinPoint){
//        Object rs = null;
//        if(PageParam.getIsPage()){
//            PageHelper.startPage(PageParam.getPageNum(),9999);
//            try {
//                rs = joinPoint.proceed();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        }
//
//
//        return rs;
//    }
}
