package kr.co.finotek.core.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;


@Configuration
@EnableRedisRepositories
public class RadisConfig {
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(
				new RedisStandaloneConfiguration(host, port)
		);
	}
	
	/**
	 * 전역 캐시 설정
	 */
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(60))
			.disableCachingNullValues()
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
					new GenericJackson2JsonRedisSerializer()));
	}
	
	/**
	 * 커스텀 캐시 설정
	 */
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return (builder) -> builder
				.withCacheConfiguration("cache1",
						RedisCacheConfiguration.defaultCacheConfig()
						.computePrefixWith(cacheName -> "cache1::" + cacheName + "::")
						.entryTtl(Duration.ofMinutes(30))
						.disableCachingNullValues()
						.serializeValuesWith(
								RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
								))
				.withCacheConfiguration("cache2",
						RedisCacheConfiguration.defaultCacheConfig()
						.entryTtl(Duration.ofHours(2))
						.disableCachingNullValues());
	}
}
