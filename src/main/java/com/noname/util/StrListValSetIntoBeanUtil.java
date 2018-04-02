package com.noname.util;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class StrListValSetIntoBeanUtil {

    public static void set(ArrayList<String> strs, Integer[] mapping, Class<T> clazz, Object obj) throws IllegalAccessException {
        Field[] fields= clazz.getFields();

        int index = 0;
        for(Field field : fields){
            field.set(obj, strs.get(mapping[index]));
            index ++;
        }

    }
}
