package com.pdd.dao;

import java.util.Set;
import org.apache.ibatis.annotations.Param;
import com.pdd.bean.User;

public interface userDao {
	//��¼
	User login(@Param("sname")String name);
	//ע��
	void regis(User usrt);
	//��ȡ��ɫ
	String getRole(@Param("sname")String uname);
	//��ȡȨ��
	Set<String> getPermission(@Param("sname")String uname);
	//�����û�ʱĬ��Ȩ��
	void addRole(@Param("uid")int uid);
}
