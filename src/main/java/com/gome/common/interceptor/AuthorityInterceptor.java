package com.gome.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxMenuService;

public class AuthorityInterceptor implements HandlerInterceptor{

	public static Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);
	
	@Resource
	private HxMenuService hxMenuService;
	
	//处理请求之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;
		logger.info("拦截到请求 ： "+request.getRequestURI());
		String path = request.getContextPath();
        String uri = request.getRequestURI();
        List<String> authorityAllList = new ArrayList<String>();//所有的权限
        List<HxMenu> menuList = hxMenuService.getAllMenuTree();
        if(menuList!=null){
        	if(menuList.size()>0){
        		for (HxMenu menu : menuList) {
        			if(menu!=null){
        				String url = menu.getUrl();
            			if(StringUtils.isNotBlank(url)){
            				authorityAllList.add(path+"/"+url);
            			}
        			}
        		}
        	}
        }
        List<String> authorityList = new ArrayList<String>();//自己拥有的权限
        if(request.getSession().getAttribute(Constrants.USER_INFO) != null){
        	SysUser sysuser = (SysUser) request.getSession().getAttribute("user");
        	HxUserPosition userPosition = new HxUserPosition();
        	String userLoginName = sysuser.getUserAccount();
        	Integer fromType = sysuser.getFromType() ;//用户来源 1国美 2第三方 3系统自建
        	logger.info("请求来自用户 ： "+userLoginName);
        	userPosition.setUserLoginName(userLoginName);
        	userPosition.setFromType(fromType);
        	List<HxMenu> userMenuList = hxMenuService.getUserMenus(userPosition);
        	if(userMenuList!=null){
            	if(userMenuList.size()>0){
            		for (HxMenu menu : userMenuList) {
            			if(menu!=null){
            				String url = menu.getUrl();
                			if(StringUtils.isNotBlank(url)){
                				authorityList.add(path+"/"+url);
                			}
            			}
            		}
            	}
            }
        }
        //如果在所有的权限之外，则不拦截
        if(!authorityAllList.contains(uri)){
        	 for(String authority : authorityAllList){
             	if(uri.startsWith(authority)){
             		logger.info(uri+"    ---    "+authority);
             		if(!checkAuthority(uri,authorityList)){
             			//showMessage(request,response);
             			//flag = false;
             			logger.info("请求：  "+uri+ " 是否拥有权限： "+ flag+" （模式1）");
             		}
             	}
        	 }
        }else{
        	if(!checkAuthority(uri,authorityList)){
     			//showMessage(request,response);
     			//flag = false;
     			logger.info("请求：  "+uri+ " 是否拥有权限： "+ flag+" （模式2）");
     		}
        }
        return flag;
	}
	
	//处理请求之后调用
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//显示视图后被调用
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkAuthority(String uri,List<String> authorityList){
		boolean flag = false;
		for(String authority : authorityList){
        	if(uri.startsWith(authority)){
        		flag = true;
        		break;
        	}else {
        		flag = false;
        	}
        }
		return flag;
	}
	
	public void showMessage(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");         
    	response.setContentType("text/html; charset=utf-8");  
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
		builder.append("alert('对不起，您没有权限！');window.history.go(-1);");
		builder.append("</script>");
		out.print(builder.toString());
		out.close();
	}

}
