package com.noname.constant;

public class CSSubjectConst {

    public static final class ClientOrManage{
        /**
         * 前端平台的登陆
         */
        public static final int CLIENT = 0;

        /**
         * 管理人员
         */
        public static final int MANAGE = 1;
    }

    public static final class time {

        /**
         * 3天
         */
        public static final long THREE_DAY = 3;

        /**
         * 10天
         */
        public static final long TEN_DAY = 10;

        /**
         * 以秒为单位3天时间
         */
        public static final long THREE_DAY_IN_MILLISECOND = 1000 * 60 * 60 * 24 * THREE_DAY;


        /**
         * 以秒为单位3天时间
         */
        public static final long TEN_DAY_IN_MILLISECOND = 1000 * 60 * 60 * 24 * TEN_DAY;

        /**
         * 15分钟
         */
        public static final long MINUTE_15 = 1000 * 60 * 15;

        /**
         * 30分钟
         */
        public static final long MINUTE_30 = 1000 * 60 * 30;
    }

    /**
     * token类型
     */
    public static final class type {

        /**
         * 访问的token
         */
        public static final String ACCESS_TOKEN = "accessToken";


        /**
         * 刷新的token
         */
        public static final String REFRESS_TOKEN = "refressToken";

    }
}
