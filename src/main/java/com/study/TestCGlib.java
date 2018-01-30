package com.study;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.java2d.pipe.SpanIterator;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestCGlib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargeInterceptor());
        TargetObject targetObject2 = (TargetObject)enhancer.create();
        System.out.println(targetObject2);
        targetObject2.say1();
        targetObject2.say2();
    }
}

class TargeInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置通知");
        Object rs = methodProxy.invokeSuper(o, objects);
        System.out.println("后置通知");
        return rs;
    }
}

class TargetObject{
    TargetObject(){
        System.out.println("consturct");
    }
    public void say1(){
        System.out.println("wahahahaha");
    }
    public void say2( ){
        System.out.println("wakakakaka");
    }
}
