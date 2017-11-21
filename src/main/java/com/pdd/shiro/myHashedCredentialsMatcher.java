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
		//初始化缓存器(可以在Ehcache配置文件中进行配置-----缓存失效时间=密码验证失败5次后的禁止登陆时间)
		this.passwordRetryCache=cacheManager.getCache("passwordRetryCache");
	}
	
	/**
	 * 自定义密码凭证器
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//1.拿到登录的用户名
		String userName=(String)token.getPrincipal();
		//使用AtomicInteger计数器进行计数
		AtomicInteger retryCount =passwordRetryCache.get(userName);
		if(retryCount==null){
			retryCount=new AtomicInteger(0);
			passwordRetryCache.put(userName, retryCount);
		}
		//统计登录失败次数(并且自增一次)
		if(retryCount.incrementAndGet()>5){
			throw new ExcessiveAttemptsException();
		}
		//调用父类的密码匹配方法 验证密码是否正确(正确返回:true)
		boolean bool=super.doCredentialsMatch(token, info);
		//如果密码正确则将统计次数从缓存中移除
		if(bool){
			passwordRetryCache.remove(userName);
		}
		return bool;
	}
}
