package com.noname.bo;

public class DataResult<T> extends Result {

    T data;
    public DataResult(){
        super();
    }

    public DataResult(T t){
        this.data = t;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
