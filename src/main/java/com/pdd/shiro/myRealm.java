package com.pdd.shiro;

import java.util.HashSet;
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
import com.pdd.service.userService;

public class myRealm extends AuthorizingRealm {
	
	@Autowired
	private userService udao;
	
	@Override	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		  //����Ȩ�޻�ȡ��
		  //ÿ������ʱ��֤һ�� ���л����򲻽��˷���
		  SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		  User user=(User)principals.getPrimaryPrincipal();
//		  //���ý�ɫ
		  String roles=udao.getRole(user.getSname());
		  if(roles.equals("��ͨ�û�")){
			  //ֱ���׳���Ȩ�޷����쳣
			  throw new UnauthorizedException();
		  }
		  Set<String> role=new HashSet<String>();
		  role.add(roles);
		  System.out.println(role);
		  authorizationInfo.setRoles(role);
//		  //����Ȩ��
		  Set<String> permission=udao.getPermission(user.getSname());
		  System.out.println(permission);
		  authorizationInfo.setStringPermissions(permission);
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
