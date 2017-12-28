package com.noname.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {

    private static final String IS_NUMBER_REGEX = "^-?[0-9]+$";

    public static Boolean isNumber(String string){

        Pattern pattern = Pattern.compile(IS_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()){
            return true;
        }
        return false;
    }
}
