package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@Component
public class RedisCache {
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 缓存的key值
	 * @param value 缓存的value值
	 */
	public <T> void setCacheObject(final String key, final T value){
		redisTemplate.opsForValue().set(key,value);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 缓存的key值
	 * @param value 缓存的value值
	 * @param timeout 时间
	 * @param timeUnit 时间颗粒度
	 */
	public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit){
		redisTemplate.opsForValue().set(key,value,timeout, timeUnit);
	}

	/**
	 * 设置有效期
	 * @param key redis键
	 * @param timeout 超时时间
	 * @return  true=设置成功；false=设置失败
	 */
	public boolean expire(final String key, final long timeout){
		return expire(key,timeout,TimeUnit.SECONDS);
	}

	/**
	 * 设置有效期
	 * @param key redis键
	 * @param timeout 超时时间
	 * @param unit 时间单位
	 * @return  true=设置成功；false=设置失败
	 */
	private boolean expire(final String key, final long timeout, final TimeUnit unit) {
		return redisTemplate.expire(key,timeout,unit);
	}

	/**
	 * 获得缓存的基本对象
	 * @param key 缓存键值
	 * @return 键值对应的数据
	 */
	public <T> T getCacheObject(final String key){
		ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	/**
	 * 删除单个对象
	 */
	public boolean deleteObject(final String key){
		return redisTemplate.delete(key);
	}

	/**
	 * 删除集合对象
	 */
	public long deleteObject(final Collection collection){
		return redisTemplate.delete(collection);
	}

	/**
	 * 缓存List数据
	 */
	public <T> long setCacheList(final String key, final List<T> dataList){
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null?0:count;
	}

	/**
	 * 获得缓存的list对象
	 */
	public <T> List<T> getCacheList(final String key){
		return redisTemplate.opsForList().range(key,0,-1);
	}

	/**
	 * 缓存set
	 */
	public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet){
		BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
		Iterator<T> iterator = dataSet.iterator();
		while (iterator.hasNext()){
			setOperations.add(iterator.next());
		}
		return setOperations;
	}

	/**
	 * 获得缓存的set
	 */
	public <T> Set<T> getCacheSet(final String key){
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 缓存Map
	 */
	public <T> void setCacheMap(final String key, final Map<String, T> dataMap){
		if (dataMap != null){
			redisTemplate.opsForHash().putAll(key,dataMap );
		}
	}

	/**
	 * 获得缓存的set
	 */
	public <T> Map<String, T> getCacheMap(final String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 往hash中存入数据
	 * @param key redis键
	 * @param hKey hash键
	 * @param value 值
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value){
		redisTemplate.opsForHash().put(key,hKey,value);
	}

	/**
	 * 获取hash中的数据
	 * @param key redis键
	 * @param hKey hash键
	 * @return hash中的对象
	 */
	public <T> T getCacheMapValue(final String key, final String hKey){
		HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
		return hashOperations.get(key,hKey);
	}

	/**
	 * 删除hash中的数据
	 * @param key redis键
	 * @param hKey hash键
	 * @return hash中的对象
	 */
	public void delCacheMapValue(final String key, final String hKey){
		HashOperations hashOperations = redisTemplate.opsForHash();
		hashOperations.delete(key,hKey);
	}

	/**
	 * 获取多个Hash中的数据
	 * @param key redis键
	 * @param hKeys Hash键集合
	 * @return Hash对象集合
	 */
	public <T> List<T> getMultiCacheValue(final String key, final Collection<Object> hKeys){
		return redisTemplate.opsForHash().multiGet(key,hKeys);
	}

	/**
	 * 获得缓存的基本对象列表
	 * @param pattern 字符串前缀
	 * @return 对象列表
	 */
	public Collection<String> keys(final String pattern){
		return redisTemplate.keys(pattern);
	}


}
