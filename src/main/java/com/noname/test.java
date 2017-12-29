package com.noname;

import java.text.SimpleDateFormat;

public class test {

    private static final int num = 10;
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(num == new Integer(10));
        System.out.println(new Integer(10) == num);
    }
}
