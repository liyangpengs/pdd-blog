package com.pdd.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {

	}
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		if(null!=session.getAttribute("ImgCache")){
			String str=(String)session.getAttribute("ImgCache");
			String [] split=str.split(",");
			EliminateCacheImg.DelImg(split, session);
		}
	}
}
