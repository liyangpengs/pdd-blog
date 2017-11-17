package com.pdd.utils;

import java.io.File;

import javax.servlet.http.HttpSession;

public class EliminateCacheImg {
	
	public static void DelImg(String [] imgName,HttpSession session){
		for (String string : imgName) {
			String files=session.getServletContext().getRealPath("static/uploadImg")+"/"+string;
			File file=new File(files);
			if(file.exists()){
				file.delete();
			}
		}
	}
}
