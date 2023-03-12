package com.example.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @author 张丽璇
 * @date 2023/3/4
 */
public class RedisSessionDao extends CachingSessionDAO {
	private static final String SHIRO_SESSION_KEY = "shiro_session_key";

	@Autowired
	RedisTemplate redisTemplate; //spring-data-redis 读写操作
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doUpdate(Session session) {
		this.saveSession(session);
	}

	@Override
	protected void doDelete(Session session) {
		Serializable sessionId = session.getId();
		if (sessionId == null){
			logger.error("session id is null");
			return;
		}
		redisTemplate.boundHashOps(RedisSessionDao.SHIRO_SESSION_KEY)
				.delete(sessionId);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session,sessionId); //分配会话id
		this.saveSession(session);
		return sessionId;
	}

	/**
	 * 保存进redis中
	 * @param session 要保存的session
	 */
	private void saveSession(Session session) {
		Serializable sessionId = session.getId();
		if (sessionId == null){
			logger.error("session id is null");
			return;
		}
		redisTemplate.boundHashOps(RedisSessionDao.SHIRO_SESSION_KEY)
				.put(sessionId,session);
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null){
			logger.error("传入的session id is null");
			return null;
		}
		return (Session) redisTemplate.boundHashOps(RedisSessionDao.SHIRO_SESSION_KEY)
				.get(sessionId);
	}
}
