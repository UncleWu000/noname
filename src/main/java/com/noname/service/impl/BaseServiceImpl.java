//package com.noname.service.impl;
//
//import com.noname.mapper.BaseMapper;
//import com.noname.service.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
//
//
//public class BaseServiceImpl<D extends BaseMapper<T>, T> implements BaseService<T>{
//
//    @Autowired
//    protected D dao;
//
//    @Override
//    public List<T> select(T t) {
//        return dao.select(t);
//    }
//
//    @Override
//    public List<T> selectAll() {
//        return dao.selectAll();
//    }
//
//    @Override
//    public int insert(T t) {
//        return dao.insert(t);
//    }
//
//    @Override
//    public int insertSelective(T t) {
//        return dao.insertSelective(t);
//    }
//
//    @Override
//    public T selectByPrimaryKey(Object id) {
//        return dao.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int updateByPrimaryKey(T t) {
//        return dao.updateByPrimaryKey(t);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(T t) {
//        return dao.updateByPrimaryKeySelective(t);
//    }
//
//    @Override
//    public int deleteByPrimaryKey(Object id) {
//        return dao.deleteByPrimaryKey(id);
//    }
//
//
//}
