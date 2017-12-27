package com.noname.util;

import java.util.Date;

public class EncrypUtils {

    public static String encryp(String username, String password) throws Exception{
        String dateStr = DateUtils.format(new Date());
        String before = username+dateStr+password;
        byte[] inDate = before.getBytes();
        byte[] firstCode = Coder.encryptMD5(inDate);
        String token = Coder.encryptBASE64(firstCode);
        return token;
    };

}
