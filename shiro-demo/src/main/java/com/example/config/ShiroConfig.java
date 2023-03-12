package com.example.config;

import com.example.filter.SessionCheckFilter;
import com.example.shiro.UserRealm;
import com.example.shiro.RedisCacheManager;
import com.example.shiro.RedisSessionDao;
import com.example.shiro.TokenWebSessionManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@Configuration
public class ShiroConfig {

	/**
	 * 过滤器，配置哪些需要shiro处理
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> customFilterMap = new HashMap<>();
		//使用自动注入无效，猜测跟加载顺序有关
		//注意：给自定义过滤器起个名字，名字——>过滤器（名字映射到过滤器），将过滤器作用到指定路径上
		customFilterMap.put("hasToken", new SessionCheckFilter());
		shiroFilterFactoryBean.setFilters(customFilterMap);
		// shiroFilterFactoryBean.setLoginUrl("/login");
		//控制 访问xx资源 需要xx权限
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("/login", "anon"); //登录请求放行
		// filters.put("/users/**", "authc"); //访问用户，需要认证（登录）
		// filters.put("/users/getAll", "perms[users:select]");
		filters.put("/users/**", "hasToken, roles[role_user]");
		// filters.put("/logout","logout");//可以准备一个controller方法，利用subject的方法进行退出
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
		return shiroFilterFactoryBean;
	}

	/**
	 * 安全管理器
	 */
	@Bean
	public DefaultWebSecurityManager defaultSecurityManager(Realm realm, SessionManager sessionManager){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//如果不是前后端分离，可以不设置
		securityManager.setSessionManager(sessionManager);
		securityManager.setRealm(realm);
		return securityManager;
	}

	/**
	 * realm
	 */
	@Bean
	public Realm realm(CredentialsMatcher matcher, CacheManager cacheManager){
		UserRealm realm = new UserRealm();
		realm.setCredentialsMatcher(matcher);
		realm.setCacheManager(cacheManager); //配置缓存管理器
		realm.setCachingEnabled(true);//开启全局缓存
		return realm;
	}

	/**
	 * 凭证匹配器
	 */
	@Bean
	public CredentialsMatcher credentialsMatcher(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		//设置散列算法：这里使用的MD5算法
		matcher.setHashAlgorithmName("md5");
		//散列次数，好比散列2次，相当于md5(md5(xxxx))
		matcher.setHashIterations(1);
		return matcher;
	}

	/**
	 * 自定义会话管理器，
	 *  前后端分离下，解决前端跨域请求cookie传输不到服务器问题，
	 *  将sessionId作为token，登录后后台返回token
	 *  前端每次请求时都在header中带上这个token作为会话
	 *  后台服务才能识别为同一个会话
	 */
	@Bean
	public SessionManager sessionManager(SessionDAO sessionDAO){
		DefaultWebSessionManager sessionManager = new TokenWebSessionManager();
		//配置session持久化
		sessionManager.setSessionDAO(sessionDAO);
		//设置会话过期时间，即token有效期
		sessionManager.setGlobalSessionTimeout(3*60*1000);
		sessionManager.setDeleteInvalidSessions(true); //默认调用sessionDao的delete方法删除
		//设置会话定时检查
		sessionManager.setSessionValidationInterval(180000); //默认一个小时
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}

	/**
	 * 自定义session持久化
	 *
	 * 用redis解决集群之间session共享问题
	 * session默认是存储在本地tomcat中的，默认存储半个小时。
	 * 不同服务器之间无法共享session，将session放到第三方，
	 * redis是基于内存操作的，读取速度快
	 */
	@Bean
	public SessionDAO sessionDAO(){
		return new RedisSessionDao();
	}

	/**
	 * 自定义缓存管理器
		 * 每次访问有权限相关的判断的请求时，都会执行doGetAuthorizationInfo()方法
		 * 可以缓存授权权限信息，不需要每次都查询数据库赋权
	     * 缓存每一个用户的角色和权限信息，减少数据库的压力，提高性能
	 * 跟缓存session的操作一样，只不过继承的类和用于hash的key不一样
	 */
	@Bean
	public CacheManager cacheManager(){
		return new RedisCacheManager();
	}

}
