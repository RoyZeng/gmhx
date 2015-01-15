package com.gome.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gome.gmhx.entity.SysUser;

public class SessionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Exception {
		SysUser sysUser = (SysUser)req.getSession().getAttribute("user");
		if(sysUser == null){
			resp.setCharacterEncoding("utf-8");         
		    resp.setContentType("text/html; charset=utf-8");  
			PrintWriter out = resp.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("alert('页面超时，请重新登录。！');");
			builder.append("parent.location.href='");
			builder.append(req.getContextPath());
			builder.append("';</script>");
//			$.messager.alert('','页面超时，请重新登录。！',null,function(){window.location.href='';});
			out.print(builder.toString());
			out.close();
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}
	
}
