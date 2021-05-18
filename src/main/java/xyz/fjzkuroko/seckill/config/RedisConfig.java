package xyz.fjzkuroko.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis的配置类
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/12 16:27
 */
@Configuration
public class RedisConfig {

    // 自定义RedisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //key序列化
        template.setKeySerializer(new StringRedisSerializer());
        //value序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash类型 key序列化
        template.setHashKeySerializer(new StringRedisSerializer());
        //hash类型 value序列化
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 注入连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
