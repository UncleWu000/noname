package com.noname;

import java.util.Date;

public class test {

    private static final int num = 10;
    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println(num == new Integer(10));
//        System.out.println(new Integer(10) == num);
        Date time1 = new Date(18,2,28);
        Date time2 = new Date(18,2,27);

        System.out.println(time1+"  --  "+time2);
        System.out.println(time1.before(time2));
        System.out.println(time1.compareTo(time2));
    }
}
