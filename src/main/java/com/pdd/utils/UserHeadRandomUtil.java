package com.pdd.utils;

public class UserHeadRandomUtil {
	private static final String [] headImg={
		"/static/imgs/userface1.jpg",
		"/static/imgs/userface2.jpg",
		"/static/imgs/userface3.jpg",
		"/static/imgs/userface4.jpg",
		"/static/imgs/userface5.jpg",
	};
	/**
	 * ��������û�ͷ��
	 * @return
	 */
	public static String getHeadImg(){
		int index = (int) (Math.random() * headImg.length);
		return headImg[index];
	}
}
