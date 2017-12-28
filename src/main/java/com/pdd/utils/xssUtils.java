package com.pdd.utils;

public class xssUtils {
	/**
	 * xss¹ıÂË
	 * @param str
	 * @return
	 */
	public static String xssFilter(String str){
		str=str.replace("&", "&amp;");
		str=str.replace("<", "&lt;");
		str=str.replace(">", "&gt;");
		str=str.replace("\"", "&quot;");
		str=str.replace("'", "&#39");
		str=str.replace(" ", "&nbsp;");
		return str;
	}
}
