package com.home.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description @EnableCaching开启缓存注解支持,序列号核心，对象转字节，方便磁盘读取
 * @Description @SuppressWarnings省略，看看Jackson的提示问题
 * @author Mr.X
 *
 **/
@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    /**
     * @Overwrise redisTemplate
     * @Serializer String,Json
     * @ObjectMapper from jackson
     * @return
     */
    @Bean

    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //Key:String 序列化
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        //value:Jackson 序列化
        Jackson2JsonRedisSerializer j2S=new Jackson2JsonRedisSerializer(Object.class);
        //数据模型转化(java->json)
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //反序列，除final类
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //Jackson2JsonRedisSerializer装载序列化模型
        j2S.setObjectMapper(objectMapper);
        //key序列化设置
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //value序列号设置
        redisTemplate.setValueSerializer(j2S);
        redisTemplate.setHashValueSerializer(j2S);
        return redisTemplate;
    }

    /**
     *
     * @Description 缓存管理
     * @return
     */
    @Bean

    public CacheManager cacheManager(LettuceConnectionFactory factory){
        //Redi缓存书写器
        RedisCacheWriter redisCacheWriter=RedisCacheWriter.lockingRedisCacheWriter(factory);
        //Rediscontext序列化
        RedisSerializationContext.SerializationPair pair=RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        RedisCacheConfiguration redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    /**
     * @Description key自定义
     * @return
     */
    @Bean

    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                Arrays.asList(params).stream().forEach(item -> {
                    sb.append(item.toString());
                });
                return sb.toString();
            }
        };
    }



}
