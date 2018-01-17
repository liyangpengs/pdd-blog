package com.pdd.dao;

import org.apache.ibatis.annotations.Param;
import com.pdd.bean.User;

public interface userDao {
	//µÇÂ¼
	User login(@Param("sname")String name);
	//×¢²á
	void regis(User usrt);
}
