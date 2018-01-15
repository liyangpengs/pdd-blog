package com.pdd.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {
	private Jedis redis;
	@Autowired
	public void setPoole(JedisPool poole) {
		redis=poole.getResource();
	}
	/**
	 * 将集合添加至Redis列表
	 * @param key 键
	 * @param list 集合
	 */
	public <T> void rpush(String key,List<T> list){
		for (int i = 0; i < list.size(); i++) {
			redis.rpush(key, list.get(i).toString());
		}
		//默认缓存30分钟
		redis.expire(key, 60*30);
	}
	/**
	 * 通过Key去Redis查询
	 * @param key
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key,int begin,int end){
		return redis.lrange(key, begin, end);
	}
	/**
	 * redis操作普通字符串并且设置超时删除
	 * @param key
	 * @param value
	 * @param timeOut
	 */
	public void StrSet(String key,String value,int timeOut){
		redis.set(key, value);
		redis.expire(key, timeOut);
	}
	public void StrSet(String key,String value){
		redis.set(key, value);
	}
	/**
	 * Redis更新列表数据
	 * @param key   要修改的键
	 * @param oldValue 要修改的键的老值
	 * @param newValue 要修改的键的新值
	 */
	public void lupdate(String key,String oldValue,String newValue){
		redis.lrem(key, 0, oldValue);
		redis.rpush(key, newValue);
	}
	
	public String get(String key){
		return redis.get(key);
	}
	/**
	 * 删除Key
	 * @param key
	 * @return
	 */
	public long del(String key){
		return redis.del(key);
	}
	/**
	 * 判断hash中users键key字段是否存在
	 * @param key
	 * @return
	 */
	public boolean hexists(String key){
		return redis.hexists("users", key);
	}
	/**
	 * 设置hash中users键key字段的value
	 * @param key
	 * @param value
	 */
	public long hset(String key,String value){
		return redis.hset("users", key, value);
	}
}



