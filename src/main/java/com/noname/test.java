package com.noname;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(new Date(1482904713L));
    }
}
