package com.pdd.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class SerializableUtil {
	/**
	 * 序列化工具
	 * @param obj
	 * @return
	 */
	public static String Serialize(Object obj){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos=new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(bos.toByteArray());
	}
	/**
	 * 反序列化工具
	 * @param str
	 * @return
	 */
	public static Object deSerialize(String str){
		ByteArrayInputStream bis=new ByteArrayInputStream(Base64.getDecoder().decode(str));
		ObjectInputStream ois;
		Object obj=null;
		try {
			ois = new ObjectInputStream(bis);
			obj=ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
