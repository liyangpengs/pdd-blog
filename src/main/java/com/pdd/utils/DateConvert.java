package com.pdd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 * 日期类型转换器
 * @author maki
 *
 */
@Component
public class DateConvert implements Converter<String, Date>{
	
	private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Date convert(String source) {
		try {
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
