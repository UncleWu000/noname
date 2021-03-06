package com.noname.constant;

public class ResultConst {

    public static class status {

        /**
         * 失败
         */
        public static final String FALSE = "false";

        /**
         * 成功
         */
        public static final String TRUE = "true";
    }


    public static class code {

        /**
         * 成功
         */
        public static final String SUCCESS = "200";


        /**
         * 授权成功
         */
//		public static final String SUCCESS_AUTHORIZED = "201";

        /**
         * 未授权
         */
        public static final String UNAUTHORIZED = "401";


        /**
         * token不能为空（需要传递token但是没有传递）
         */
        public static final String UNAUTHORIZED_NO_TOKEN = "4010";

        /**
         * 不合法的token（系统无法解析）
         */
        public static final String UNAUTHORIZED_INVALID = "4011";

        /**
         * token已经失效（过期）
         */
        public static final String UNAUTHORIZED_EXPIRED = "4012";

        /**
         * token合法，但是用户还没有绑定我们系统的用户信息
         */
        public static final String UNAUTHORIZED_UNREGIST = "4013";


        /**
         * 系统异常
         */
        public static final String EXCEPTION = "500";

        /**
         * 验证错误
         */
        public static final String ERROR = "501";
    }
}
