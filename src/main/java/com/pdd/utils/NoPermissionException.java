package com.pdd.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import com.alibaba.fastjson.JSON;
import com.pdd.vo.JsonData;

/**
 * 自定义没有权限异常
 * @author Admin
 *
 */
public class NoPermissionException extends SimpleMappingExceptionResolver{

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//配置模型视图
		ModelAndView mav=new ModelAndView("redirect:/view/admin/unauthorized");
		//通过请求头方式辨别请求是否属于ajax请求
		String str=request.getHeader("X-Requested-With");
		if(StringUtils.isEmpty(str)){
			//如果非ajax请求则正确返回视图
			return mav;
		}
		//这是我自定义的json对象
		JsonData json=new JsonData();
		json.setCode(100);
		//设置消息提示
		json.setMassage("哈哈,你没有权限");
		//设置响应编码(我这边设置响应头code为200 但是请求依然返回500)
		response.setStatus(response.SC_OK);
		//设置响应内容类型
		response.setContentType("application/json; charset=utf-8");
		//设置响应编码
		response.setCharacterEncoding("utf-8");
		try {
			//使用fastJson插件生成json交由response返回输入到前台
			response.getWriter().println(JSON.toJSONString(json));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}	
