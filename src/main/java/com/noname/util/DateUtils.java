package com.noname.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String YMD = "yyyy-MM-dd";

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static String format(Date date, String format){
        sdf.applyPattern(YMD);
        return sdf.format(date);
    }
}
