package com.example.shiro;

import com.example.exception.AuthenticationException;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 用户每次请求都会带入这个id，后台服务才能识别是同一个会话。
 *
 * 前后端分离下，我们可以用这个sessionId作为token。登录后后台返回token，前端每次请求都在header中带上这个token作为会话。
 */
public class TokenWebSessionManager extends DefaultWebSessionManager {
	public static final String AUTHORIZATION = "token";


	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		//从请求头header中获取session
		String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		System.out.println("sessionId="+sessionId);
		if(sessionId != null){
			//sessionId存放的位置
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
					"Stateless request");
			//sesssionId的值
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
			//是否验证
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sessionId;
		}else {
			// //否则按默认规则从cookie取sessionId
			// return super.getSessionId(request,response);
			//抛出异常
			return null;
		}

	}

}
