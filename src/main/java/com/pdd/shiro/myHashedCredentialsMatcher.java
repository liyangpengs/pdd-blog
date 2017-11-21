package com.pdd.shiro;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager; 

public class myHashedCredentialsMatcher extends HashedCredentialsMatcher{
	private Cache<String, AtomicInteger> passwordRetryCache;  
	
	public myHashedCredentialsMatcher() {
	}
	
	public myHashedCredentialsMatcher(CacheManager cacheManager) {
		//��ʼ��������(������Ehcache�����ļ��н�������-----����ʧЧʱ��=������֤ʧ��5�κ�Ľ�ֹ��½ʱ��)
		this.passwordRetryCache=cacheManager.getCache("passwordRetryCache");
	}
	
	/**
	 * �Զ�������ƾ֤��
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//1.�õ���¼���û���
		String userName=(String)token.getPrincipal();
		//ʹ��AtomicInteger���������м���
		AtomicInteger retryCount =passwordRetryCache.get(userName);
		if(retryCount==null){
			retryCount=new AtomicInteger(0);
			passwordRetryCache.put(userName, retryCount);
		}
		//ͳ�Ƶ�¼ʧ�ܴ���(��������һ��)
		if(retryCount.incrementAndGet()>5){
			throw new ExcessiveAttemptsException();
		}
		//���ø��������ƥ�䷽�� ��֤�����Ƿ���ȷ(��ȷ����:true)
		boolean bool=super.doCredentialsMatch(token, info);
		//���������ȷ��ͳ�ƴ����ӻ������Ƴ�
		if(bool){
			passwordRetryCache.remove(userName);
		}
		return bool;
	}
}
