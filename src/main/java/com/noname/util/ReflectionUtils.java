package com.noname.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * 反射工具类
 * 
 */  
  
public class ReflectionUtils {  
  
    /** 
     * 循环向上转型, 获     * @param object : 子类对象 
     * @param methodName : 父类中的方法名 
     * @param parameterTypes : 父类中的方法参数类型 
     * @return 父类中的方法对象 
     */  
      
    public static Method getDeclaredMethod(Object object, String methodName, Class<?> ... parameterTypes){  
        Method method = null ;  
          
        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;  
                return method ;  
            } catch (Exception e) {  
                //这里甚么都不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会进入              
            }  
        }  
          
        return null;  
    }  
      
    /** 
     * 直接调用对象方法, 而忽略修饰符(private, protected, default) 
     * @param object : 子类对象 
     * @param methodName : 父类中的方法名 
     * @param parameterTypes : 父类中的方法参数类型 
     * @param parameters : 父类中的方法参数 
     * @return 父类中方法的执行结果 
     */  
      
    public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,  
            Object [] parameters) {  
        //根据 对象、方法名和对应的方法参数 通过取 Method 对象  
        Method method = getDeclaredMethod(object, methodName, parameterTypes) ;  
          
        //抑制Java对方法进行检查,主要是针对私有方法而言  
        method.setAccessible(true) ;  
          
            try {  
                if(null != method) {  
                      
                    //调用object 的 method 所代表的方法，其方法的参数是 parameters  
                    return method.invoke(object, parameters) ;  
                }  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                e.printStackTrace();  
            }  
          
        return null;  
    }  
  
    /** 
     * 循环向上转型, 获     * @param object : 子类对象 
     * @param fieldName : 父类中     * @return 父类中     */  
      
    public static Field getDeclaredField(Object object, String fieldName){  
        Field field = null ;  
          
        Class<?> clazz = object.getClass() ;  
          
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                field = clazz.getDeclaredField(fieldName) ;  
                return field ;  
            } catch (Exception e) {  
                //这里甚么都不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会进入                  
            }   
        }  
      
        return null;  
    }     
      
    /** 
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也     * @param object : 子类对象 
     * @param fieldName : 父类中     * @param value : 将要设置的值 
     */  
      
    public static void setFieldValue(Object object, String fieldName, Object value){  
      
        //根据 对象和属性名通过取 Field对象  
        Field field = getDeclaredField(object, fieldName) ;  
          
        //抑制Java对其的检查  
        field.setAccessible(true) ;  
          
        try {  
            //将 object 中 field 所代表的值 设置为 value  
             field.set(object, value) ;  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
          
    }  
      
    /** 
     * 直接读的属性值, 忽略 private/protected 修饰符, 也     * @param object : 子类对象 
     * @param fieldName : 父类中     * @return : 父类中     */  
      
    public static Object getFieldValue(Object object, String fieldName){  
          
        //根据 对象和属性名通过取 Field对象  
        Field field = getDeclaredField(object, fieldName) ;  
          
        //抑制Java对其的检查  
        field.setAccessible(true) ;  
          
        try {  
            //获的属性值  
            return field.get(object) ;  
              
        } catch(Exception e) {  
            e.printStackTrace() ;  
        }  
          
        return null;  
    }  

    
    /**
     * 获取所有属性，包括父类的属性
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object){
	  Class clazz = object.getClass();
	  List<Field> fieldList = new ArrayList<>();
	  while (clazz != null){
	    fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
	    clazz = clazz.getSuperclass();
	  }
	  Field[] fields = new Field[fieldList.size()];
	  fieldList.toArray(fields);
	  return fields;
	}

    /**
     * 判断该对象所有属性是否为空: 
     * @param obj	需要检查的对象
     * @return		返回ture表示所有属性为null  返回false表示不是所有属性都是null
     * @throws Exception
     */
    public static boolean isAllFieldNull(Object obj) throws Exception{
        Field[] fs = getAllFields(obj);//得到属性集合（包括父类）
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            System.out.println("f=="+f.getName()+",val="+val);
            if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }
}  