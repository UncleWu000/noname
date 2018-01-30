package com.study;

import org.assertj.core.internal.cglib.proxy.Dispatcher;
import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.LazyLoader;

public class TestCGlibLazyLoader {

    public static void main(String[] args) {
        LazyBean lazyBean = new LazyBean("shiy", 23);

        PropertyBean lazyLoader = lazyBean.getPropertyBean();
        System.out.println("lazyLoader 1 --->"+lazyLoader.getKey()); //lazyLoader 只有第一次调用时候懒加载
        System.out.println("lazyLoader 2 --->"+lazyLoader.getKey());

        PropertyBean dispatcher = lazyBean.getPropertyBeanDispatcher();
        System.out.println("dispatcher 1 --->"+dispatcher.getKey()); //dispatcher 每次调用都是懒加载
        System.out.println("dispatcher 2 --->"+dispatcher.getKey());
    }
}


class LazyBean {
    private String name;
    private int age;
    private PropertyBean propertyBean;
    private PropertyBean propertyBeanDispatcher;

    public LazyBean(String name, int age) {
        System.out.println("lazy bean init");
        this.name = name;
        this.age = age;
        this.propertyBean = createPropertyBean();
        this.propertyBeanDispatcher = createPropertyBeanDispatcher();
    }



    /**
     * 只第一次懒加载
     * @return
     */
    private PropertyBean createPropertyBean() {
        /**
         * 使用cglib进行懒加载 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
         * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
         * 就会自动触发代理类回调）。
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class,
                new ConcreteClassLazyLoader());
        return pb;
    }
    /**
     * 每次都懒加载
     * @return
     */
    private PropertyBean createPropertyBeanDispatcher() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class,
                new ConcreteClassDispatcher());
        return pb;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public PropertyBean getPropertyBeanDispatcher() {
        return propertyBeanDispatcher;
    }

    public void setPropertyBeanDispatcher(PropertyBean propertyBeanDispatcher) {
        this.propertyBeanDispatcher = propertyBeanDispatcher;
    }

    @Override
    public String toString() {
        return "LazyBean [name=" + name + ", age=" + age + ", propertyBean="
                + propertyBean + "]";
    }
}

class PropertyBean {
    private String key;
    private Object value;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "PropertyBean [key=" + key + ", value=" + value + "]" +getClass();
    }

}

class ConcreteClassDispatcher implements Dispatcher {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("before Dispatcher...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("xxx");
        propertyBean.setValue(new TargetObject());
        System.out.println("after Dispatcher...");
        return propertyBean;
    }

}

class ConcreteClassLazyLoader implements LazyLoader {
    /**
     * 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
     * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
     * 就会自动触发代理类回调）。
     */
    @Override
    public Object loadObject() throws Exception {
        System.out.println("before lazyLoader...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("zghw");
        propertyBean.setValue(new TargetObject());
        System.out.println("after lazyLoader...");
        return propertyBean;
    }

}