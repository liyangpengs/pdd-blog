package com.pdd.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class getAddressUtil {
	
	public static String getAddress(String ip){
		//百度Ip地址辨别接口
		String api="https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=%s&co=&resource_id=6006&oe=utf-8&tn=baidu&qq-pf-to=pcqq.group";
		api=String.format(api, ip);
		try {
			URL url=new URL(api);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
			conn.connect();
			InputStream input=conn.getInputStream();
			int size=input.available();
			byte [] bt=new byte[size];
			input.read(bt);
			JSONObject obj=JSON.parseObject(new String(bt,"UTF-8"));
			input.close();
			conn.disconnect();
			return obj.getJSONArray("data").isEmpty()?"未知地点":obj.getJSONArray("data").getJSONObject(0).getString("location").split("\\s")[0];
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "未知地点";
	}
}
