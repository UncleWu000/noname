package com.noname.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 *z77z
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

//    @Bean
//    public JedisPool redisPool() {
//    	Logger.getLogger(getClass()).info("JedisPool注入成功");
//        Logger.getLogger(getClass()).info("redis地址：" + host + ":" + port);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//
//        return jedisPool;
//    }



    @Bean
    public CacheManager cacheManager (@SuppressWarnings("rawtypes")RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        Map<String, Long> expiredTimeMap = new HashMap<>();
        expiredTimeMap.put("articlelist", 10L);
        //设置缓存过期时间
        //cacheManager.setDefaultExpiration(10000);
        cacheManager.setExpires(expiredTimeMap);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);//设置序列化工具
        template.afterPropertiesSet();
        return template;
    }

    private void setSerializer(StringRedisTemplate template){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.setKeySerializer(new RedisSerializer<Object>() {
//            @Override
//            public byte[] serialize(Object o) throws SerializationException {
//                return o.getClass().getName().toString().getBytes();
//            }
//
//            @Override
//            public Object deserialize(byte[] bytes) throws SerializationException {
//                try {
//                    return new String(bytes, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                return "";
//            }
//        });

    }


    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("." + method.getName());
                for (Object obj : params) {
                    sb.append(":" + obj.toString());
                }
                return sb.toString();
            }
        };
    }
//
//    @Bean
//    public KeyGenerator keyGenerator2() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("keyGenerator2->");
//                sb.append(target.getClass().getName());
//                sb.append(":" + method.getName());
//                for (Object obj : params) {
//                    sb.append(":" + obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }

}


