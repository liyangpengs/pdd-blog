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
	 * �����������Redis�б�
	 * @param key ��
	 * @param list ����
	 */
	public <T> void rpush(String key,List<T> list){
		for (int i = 0; i < list.size(); i++) {
			redis.rpush(key, list.get(i).toString());
		}
		//Ĭ�ϻ���30����
		redis.expire(key, 60*30);
	}
	/**
	 * ͨ��KeyȥRedis��ѯ
	 * @param key
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key,int begin,int end){
		return redis.lrange(key, begin, end);
	}
	/**
	 * redis������ͨ�ַ����������ó�ʱɾ��
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
	 * Redis�����б�����
	 * @param key   Ҫ�޸ĵļ�
	 * @param oldValue Ҫ�޸ĵļ�����ֵ
	 * @param newValue Ҫ�޸ĵļ�����ֵ
	 */
	public void lupdate(String key,String oldValue,String newValue){
		redis.lrem(key, 0, oldValue);
		redis.rpush(key, newValue);
	}
	
	public String get(String key){
		return redis.get(key);
	}
	/**
	 * ɾ��Key
	 * @param key
	 * @return
	 */
	public long del(String key){
		return redis.del(key);
	}
	/**
	 * �ж�hash��users��key�ֶ��Ƿ����
	 * @param key
	 * @return
	 */
	public boolean hexists(String key){
		return redis.hexists("users", key);
	}
	/**
	 * ����hash��users��key�ֶε�value
	 * @param key
	 * @param value
	 */
	public long hset(String key,String value){
		return redis.hset("users", key, value);
	}
}



