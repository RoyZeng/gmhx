package com.gome.gmhx.webservice.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.CurrentSysUser;

public class SysUserFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			CurrentSysUser.setCurrentSysUser((SysUser)httpRequest.getSession().getAttribute("user"));
			chain.doFilter(request, response);
		}finally{
			CurrentSysUser.removeCurrentSysUser();
		}
	}

	@Override
	public void destroy() {
		
	}

}
