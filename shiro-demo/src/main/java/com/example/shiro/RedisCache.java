package com.example.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * @author 张丽璇
 * @date 2023/3/4
 * 通过RedisTemplate的hash操作实现缓存接口
 */
public class RedisCache<K,V> implements Cache<K, V> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

	private static final String REDIS_SHIRO_CACHE_KEY = "shiro_cache_key";
	private RedisTemplate redisTemplate;

	public RedisCache(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	@Override
	public V get(K key) throws CacheException {
		LOGGER.info("key:"+key);
		Object o = redisTemplate.opsForHash().get(RedisCache.REDIS_SHIRO_CACHE_KEY, key);
		return o==null?null:(V)o;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V old = this.get(key);
		redisTemplate.opsForHash().put(RedisCache.REDIS_SHIRO_CACHE_KEY,key,value);
		return old;
	}

	@Override
	public V remove(K key) throws CacheException {
		V old = this.get(key);
		redisTemplate.opsForHash().delete(RedisCache.REDIS_SHIRO_CACHE_KEY,key);
		return old;
	}

	@Override
	public void clear() throws CacheException {
		redisTemplate.delete(RedisCache.REDIS_SHIRO_CACHE_KEY);
	}

	@Override
	public int size() {
		return redisTemplate.opsForHash().size(RedisCache.REDIS_SHIRO_CACHE_KEY).intValue();
	}

	@Override
	public Set<K> keys() {
		return redisTemplate.opsForHash().keys(RedisCache.REDIS_SHIRO_CACHE_KEY);
	}

	@Override
	public Collection<V> values() {
		return redisTemplate.opsForHash().values(RedisCache.REDIS_SHIRO_CACHE_KEY);
	}
}
