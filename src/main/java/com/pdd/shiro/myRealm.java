package com.pdd.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.pdd.bean.User;
import com.pdd.bean.permission;
import com.pdd.bean.role;
import com.pdd.service.PermissionAndRoleService;
import com.pdd.service.userService;

public class myRealm extends AuthorizingRealm {
	
	@Autowired
	private userService udao;
	@Autowired
	private PermissionAndRoleService parsl;
	@Override	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		  //类似权限获取器
		  //每次请求时认证一次 如有缓存则不仅此方法
		  SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		  User user=(User)principals.getPrimaryPrincipal();
//		  //设置角色
		  List<role> roles=parsl.getRole(user.getSname());
		  Set<String> rset=new HashSet<>();
		  for (role role : roles) {
			  rset.add(role.getRname());
		  }
		  if(rset.contains("普通用户")){
			  //直接抛出无权限访问异常
			  throw new UnauthorizedException();
		  }
		  List<permission> permission=parsl.getPermission(user.getSname());
		  authorizationInfo.setRoles(rset);
		  Set<String> pset=new HashSet<String>();
		  for (permission pe : permission) {
			  pset.add(pe.getPurl());
		  }
		  //设置权限
		  authorizationInfo.setStringPermissions(pset);
		  return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//验证账号信息[可理解为登录]
		String username = (String)token.getPrincipal();
        User user=udao.login(username);
        if(user==null){
        	//用户不在存
        	throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		user, //此处放入用户名则getPrincipal()|getPrimaryPrincipal()方法 取到的则为用户名，放入User对象则为User对象
        		user.getSpwd(), 
        		ByteSource.Util.bytes(user.getSname()+"%%shiro-pdd-java.top%%"+user.getSalt()), //盐值加密
        		getName());
        return authenticationInfo;  
	}
}
