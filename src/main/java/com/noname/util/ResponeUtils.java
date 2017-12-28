package com.noname.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponeUtils {

    public static void writeJson(HttpServletResponse response, String json) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }
}
