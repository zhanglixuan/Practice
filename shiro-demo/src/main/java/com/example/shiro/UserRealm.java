package com.example.shiro;

import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 获取权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("获取权限信息");
		//从数据库查询用户的权限和角色
		//获取用户名 //此principal就是彼principal(认证时构造的，即下面方法所返回的)
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User)principalCollection.getPrimaryPrincipal();
		String username = user.getUsername();
		Set<String> roleByName = userService.getRoleByName(username);
		if (roleByName != null && roleByName.size() > 0){
			authorizationInfo.addRoles(roleByName);
		}
		return authorizationInfo;
	}

	/**
	 * 获取认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("获取认证信息");
		//token 是封装好的用户提交的用户名密码
		String username = ((UsernamePasswordToken) token).getUsername();
		//获取用户 封装AuthenticationInfo
		User user = userService.findByName(username);
		if (user == null) return null;
		ByteSource salt = new SimpleByteSource(user.getPrivateSalt());
		return new SimpleAuthenticationInfo(user,user.getPassword(),salt,user.getRealName());
	}
}
