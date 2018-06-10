package jp.zero.oauthserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
    private @Value("${spring.redis.host}") String redisHost;
    private @Value("${spring.redis.port}") int redisPort;
    private @Value("${spring.redis.password}") String redisPassword;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
