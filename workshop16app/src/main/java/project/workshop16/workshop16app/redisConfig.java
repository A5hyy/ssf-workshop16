package project.workshop16.workshop16app;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class redisConfig {
    
    // private static final Logger logger = LoggerFactory.getLogger(redisConfig.class);

    // @Value("${spring.redis.host}")
    // private String redisHost;

    // @Value("${spring.redis.port}")
    // private Optional<Integer> redisPort;
    
    // @Value("${spring.redis.password}")
    // private String redisPassword;

    // @Bean
    // @Scope("singleton")
    // public RedisTemplate<String, Object> redisTemplate() {
    //     final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    //     config.setHostName(redisHost);
    //     config.setPort(redisPort.get());
    //     config.setPassword(redisPassword);

    //     final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
    //     final JedisConnectionFactory jedisFactory = new JedisConnectionFactory(config, jedisClient);
    //     jedisFactory.afterPropertiesSet();

    //     final RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisFactory);
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setValueSerializer(new StringRedisSerializer());
    //     return template;
    // } 

    private static final Logger logger = LoggerFactory.getLogger(redisConfig.class);

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        config.setPassword(redisPassword);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        logger.info("redis host port > {redisHost} {redisPort}", redisHost, redisPort);
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(jedisFac);

        // Charset charset = Charset.forName("UTF-8");
        // StringRedisSerializer sds = new StringRedisSerializer();
        // template.setValueSerializer(sds);
        // template.setKeySerializer(sds);
        
        template.setKeySerializer(new StringRedisSerializer());
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        template.setValueSerializer(
            serializer
        );
        return template;
    }

}