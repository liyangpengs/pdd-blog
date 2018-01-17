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
		  //����Ȩ�޻�ȡ��
		  //ÿ������ʱ��֤һ�� ���л����򲻽��˷���
		  SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		  User user=(User)principals.getPrimaryPrincipal();
//		  //���ý�ɫ
		  List<role> roles=parsl.getRole(user.getSname());
		  Set<String> rset=new HashSet<>();
		  for (role role : roles) {
			  rset.add(role.getRname());
		  }
		  if(rset.contains("��ͨ�û�")){
			  //ֱ���׳���Ȩ�޷����쳣
			  throw new UnauthorizedException();
		  }
		  List<permission> permission=parsl.getPermission(user.getSname());
		  authorizationInfo.setRoles(rset);
		  Set<String> pset=new HashSet<String>();
		  for (permission pe : permission) {
			  pset.add(pe.getPurl());
		  }
		  //����Ȩ��
		  authorizationInfo.setStringPermissions(pset);
		  return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//��֤�˺���Ϣ[�����Ϊ��¼]
		String username = (String)token.getPrincipal();
        User user=udao.login(username);
        if(user==null){
        	//�û����ڴ�
        	throw new UnknownAccountException();
        }
        //����AuthenticatingRealmʹ��CredentialsMatcher��������ƥ�䣬��������˼ҵĲ��ÿ����Զ���ʵ��  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		user, //�˴������û�����getPrincipal()|getPrimaryPrincipal()���� ȡ������Ϊ�û���������User������ΪUser����
        		user.getSpwd(), 
        		ByteSource.Util.bytes(user.getSname()+"%%shiro-pdd-java.top%%"+user.getSalt()), //��ֵ����
        		getName());
        return authenticationInfo;  
	}
}
