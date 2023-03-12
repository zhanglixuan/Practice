package com.example.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
* @author 张丽璇
* @date 2023/3/4
*/
public class RedisCacheManager implements CacheManager {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new RedisCache<>(redisTemplate);
	}
}
