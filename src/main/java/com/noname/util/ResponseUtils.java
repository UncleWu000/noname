package com.noname.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void writeJson(HttpServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().write(JsonUtil.toJson(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
