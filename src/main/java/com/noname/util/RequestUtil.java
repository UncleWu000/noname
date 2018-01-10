package com.noname.util;

import com.noname.constant.HeaderConst;
import com.noname.constant.Sign;
import com.noname.constant.WxConst;
import com.noname.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class RequestUtil {

    /**
     * 获取请求的后缀
     *
     * @param request
     * @return
     */
    public static String getSuffix(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String suffix = StringUtils.substringAfterLast(requestUri, Sign.DOT);
        return suffix;
    }

//    /**
//     * 获取访问的渠道
//     *
//     * @param request
//     * @return
//     */
//    public static Integer getChannel(HttpServletRequest request) {
//        if (isWechat(request)) {
//            return ChannelConst.WEIXIN;
//        }
//        DevicePlatform devicePlatfrom = DeviceUtils.getCurrentDevice(request).getDevicePlatform();
//        if (DevicePlatform.ANDROID.equals(devicePlatfrom)) {
//            return ChannelConst.ANDROID;
//        } else if (DevicePlatform.IOS.equals(devicePlatfrom)) {
//            return ChannelConst.IOS;
//        }
//
//        return ChannelConst.PC;
//    }

    /**
     * 获取访问的ip
     *
     * @param request
     * @return
     */
    public static String getAccessIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

    public static final String UNKNOWN = "unknown";

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIp(HttpServletRequest request) throws ServiceException {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            throw new ServiceException("5000");
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 当前访问是否是移动端访问的。
     *
     * @param request
     * @return
     */
//    public static boolean isMobile(HttpServletRequest request) {
//        return DeviceUtils.getCurrentDevice(request).isMobile();
//    }

    /**
     *
     * 方法名: isWechat</br>
     * 详述: 是不是微信浏览器访问的</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2016年9月26日</br>
     *
     * @param request
     * @return
     */
    public static boolean isWechat(HttpServletRequest request) {
        String ua = request.getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            return true;
        }
        return false;
    }

    /**
     * 判断ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    /**
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getRequestUrl(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(
                request.getRequestURL().toString() + "?time=" + System.currentTimeMillis());
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (WxConst.WEIXIN_REQ_KEY.contains(name)) {
                continue;
            }
            String[] values = request.getParameterValues(name);
            if (values != null) {
                for (String value : values) {
                    sb.append("&" + name + "=" + value);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 获取授权的请求头
     *
     * @param request
     * @return
     */
    public static String getAuthzHeader(HttpServletRequest request) {
        return request.getHeader(HeaderConst.param.TOKEN);
    }

    public static String getAuthzHeader(ServletRequest request) {
        return getAuthzHeader((HttpServletRequest) request);
    }

    /**
     * 从请求参数中获取认证的token信息
     *
     * @param request
     * @return
     */
    public static String getAuthzParam(ServletRequest request) {
        return ((HttpServletRequest) request).getParameter(HeaderConst.param.TOKEN);
    }

}
