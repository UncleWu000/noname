package com.noname.exception;

public class UtilException extends Exception {

    public UtilException(){
        super();
    }

    public UtilException(String msg){
        super(msg);
    }

    public UtilException(String msg, Throwable cause){
        super(msg, cause);
    }

    public  UtilException(Throwable cause){
        super(cause);
    }
}
