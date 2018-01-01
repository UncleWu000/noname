package com.noname.service;

import org.springframework.stereotype.Service;
import redis.clients.util.Slowlog;

import java.util.List;


public interface RedisService {

    /**
     * 获取redis服务器信息
     * @return
     */
    String getRdeisInfo();

    /**
     * 获取日志
     * @param entries
     * @return
     */
    List<Slowlog> getLogs(long entries);

    /**
     * 获取日志数量
      * @return
     */
    Long getLogsLESN();

    /**
     * 清空日志
     * @return
     */
    String logEnpty();

    /**
     * 获取内存占用情况
     * @return
     */
    Long dbSize();
}
