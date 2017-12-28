package com.noname.util;

import com.noname.exception.UtilException;

import java.lang.reflect.Field;
import java.util.List;

public class VOUtils {

    /**
     * 把entity数据转到vo
     * @param entity
     * @param vo
     * @param <T>
     * @param <U>
     * @throws UtilException
     */
    public static <T,U> void entityDataToVo(T entity, U vo) throws UtilException {
        if(entity == null || vo ==null){
            throw new UtilException("其中一个对象为空!");
        }
        Class eClass = entity.getClass();
        Class vClass = vo.getClass();
        Field[] fields = eClass.getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            try {
                Field voField = vClass.getSuperclass().getDeclaredField(name); //获取父类私有属性字段(POJO都是私有的)
                if(voField!=null){
                    voField.setAccessible(true); //(开启修改权限)
                    voField.set(vo, field.get(entity));
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("date tran success"+entity + " to " + vo);
    }

    /**
     * 把entity数组的数据转到vo数组
     * @param enList
     * @param voList
     * @param voClass
     * @param <T>
     * @param <U>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UtilException
     */
    public static <T, U> void entityListToVoList(List<T> enList, List<U> voList, Class voClass) throws IllegalAccessException, InstantiationException, UtilException {
        if(enList == null || voList == null ||enList.size()==0)
            return;
        U vo = null;

        for(T en : enList){
            vo = (U) voClass.newInstance();
            entityDataToVo(en, vo);
            voList.add(vo);
        }
    }
}
