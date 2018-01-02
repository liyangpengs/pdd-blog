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
 * �Զ���û��Ȩ���쳣
 * @author Admin
 *
 */
public class NoPermissionException extends SimpleMappingExceptionResolver{

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//����ģ����ͼ
		ModelAndView mav=new ModelAndView("redirect:/view/admin/unauthorized");
		//ͨ������ͷ��ʽ��������Ƿ�����ajax����
		String str=request.getHeader("X-Requested-With");
		if(StringUtils.isEmpty(str)){
			//�����ajax��������ȷ������ͼ
			return mav;
		}
		//�������Զ����json����
		JsonData json=new JsonData();
		json.setCode(100);
		//������Ϣ��ʾ
		json.setMassage("����,��û��Ȩ��");
		//������Ӧ����(�����������ӦͷcodeΪ200 ����������Ȼ����500)
		response.setStatus(response.SC_OK);
		//������Ӧ��������
		response.setContentType("application/json; charset=utf-8");
		//������Ӧ����
		response.setCharacterEncoding("utf-8");
		try {
			//ʹ��fastJson�������json����response�������뵽ǰ̨
			response.getWriter().println(JSON.toJSONString(json));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}	
