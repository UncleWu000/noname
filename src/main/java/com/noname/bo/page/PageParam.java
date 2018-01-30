package com.noname.bo.page;

import java.util.Optional;

public class PageParam {

    private static ThreadLocal<Integer> PAGE_SIZE_LOCAL = new ThreadLocal<>();
    private static ThreadLocal<Integer> PAGE_NUM_LOCAL = new ThreadLocal<>();
    private static ThreadLocal<Boolean> IS_PAGE = new ThreadLocal<>();
    private static Integer PAGE_SIZE = 10;
    private static Integer PAGE_NUM  = 1;


    public static Integer getPageNum(){
        Integer pageNum = PAGE_NUM_LOCAL.get();
        return Optional.ofNullable(pageNum).orElse(PAGE_NUM);
    }

    public static Integer getPageSize(){
        Integer pageSize = PAGE_SIZE_LOCAL.get();
        return Optional.ofNullable(pageSize).orElse(PAGE_SIZE);
    }

    public static Boolean getIsPage(){
        Boolean falg = IS_PAGE.get();
        return Optional.ofNullable(falg).orElse(true);
    }

    public static void setPageSizeLocal(Integer pageSize){
        PAGE_SIZE_LOCAL.set(pageSize);
    }

    public static void setPageNumLocal(Integer pageNum){
        PAGE_NUM_LOCAL.set(pageNum);
    }

    public static void setIsPage(Boolean flag){
        IS_PAGE.set(flag);
    }
}
