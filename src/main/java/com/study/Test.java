package com.study;

import java.lang.reflect.*;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class);
        //获取代理类的构造函数, 并且传入参数InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数动态获得动态代理对象, 将自定义的InvocationHandler实例传入
        Hello hello = (Hello)constructor.newInstance(new MyInvocationHandler(new HelloImpl()));

        hello.sayHello("lalal");

        //方法二
        //生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        IHello  ihello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
//                new Class[]{IHello.class},      //一组接口
//                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
//        ihello.sayHello();
    }

}

interface Hello{
    void sayHello(String str);
}

class MyInvocationHandler implements InvocationHandler{

    private Object target;

    MyInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置处理");
        Object rs = method.invoke(target, args);
        System.out.println("后置处理");
        return null;
    }
}

class HelloImpl implements Hello{

    @Override
    public void sayHello(String str) {
        System.out.println("hello, i'm HelloImpl!!" + str);
    }
}
