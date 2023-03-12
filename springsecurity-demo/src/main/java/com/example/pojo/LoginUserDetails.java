package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@Data
@NoArgsConstructor
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 6496289497659007088L;
	private User user;
	//存储权限信息
	private List<String> permissions;
	// SimpleGrantedAuthority这个类是在springsecurity框架中，为了安全起见框架是不会让其参与序列化的
	//所以我们要忽略它，让其不会被序列化到redis中，反序列的时候再用上面的permissions进行封装
	@JSONField(serialize = false)
	private List<SimpleGrantedAuthority> authorities; //存储SpringSecurity所需要的权限信息的集合

	public LoginUserDetails(User user, List<String> permissions) {
		this.user = user;
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities != null) return authorities;
		//把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象存入到authorities中
		authorities = permissions.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { //用户是否可用状态
		return true;
	}
}
